package com.matttax.reado.audio

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVQueuePlayer
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.currentItem
import platform.AVFoundation.currentTime
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.rate
import platform.CoreMedia.CMTimeGetSeconds
import platform.Foundation.NSURL

class IosAudioPlayer : AudioPlayer {

  private val _isPlaying = MutableValue(false)
  override val isPlaying: Value<Boolean> = _isPlaying

  private val _position = MutableValue(PlaybackPosition(itemIndex = 0, positionMs = 0L))
  override val position: Value<PlaybackPosition> = _position

  private var player: AVQueuePlayer? = null
  private var items: List<AVPlayerItem> = emptyList()
  private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
  private var positionJob: Job? = null

  override fun setPlaylist(items: List<PlaylistItem>) {
    release()
    val avItems = items.mapNotNull { item ->
      val url = NSURL.URLWithString(item.url) ?: return@mapNotNull null
      val asset = AVURLAsset(
        uRL = url,
        options = mapOf<Any?, Any>("AVURLAssetHTTPHeaderFieldsKey" to item.headers),
      )
      AVPlayerItem(asset = asset)
    }
    this.items = avItems
    player = AVQueuePlayer(items = avItems)
  }

  override fun playPause() {
    val playerInstance = player ?: return
    if (playerInstance.rate != 0.0f) {
      playerInstance.pause()
      _isPlaying.value = false
      stopPositionUpdates()
    } else {
      playerInstance.play()
      _isPlaying.value = true
      startPositionUpdates()
    }
  }

  override fun release() {
    stopPositionUpdates()
    player?.pause()
    player = null
    items = emptyList()
    _isPlaying.value = false
    _position.value = PlaybackPosition(itemIndex = 0, positionMs = 0L)
  }

  @OptIn(ExperimentalForeignApi::class)
  private fun startPositionUpdates() {
    positionJob?.cancel()
    positionJob = scope.launch {
      while (isActive) {
        val p = player ?: break
        val current = p.currentItem
        val idx = if (current != null) items.indexOf(current).coerceAtLeast(0) else 0
        val seconds = current?.currentTime()?.let { CMTimeGetSeconds(it) } ?: 0.0
        val ms = if (seconds.isFinite()) (seconds * 1000.0).toLong() else 0L
        _position.value = PlaybackPosition(itemIndex = idx, positionMs = ms)
        delay(POLL_INTERVAL_MS)
      }
    }
  }

  private fun stopPositionUpdates() {
    positionJob?.cancel()
    positionJob = null
  }

  private companion object {
    const val POLL_INTERVAL_MS = 50L
  }
}
