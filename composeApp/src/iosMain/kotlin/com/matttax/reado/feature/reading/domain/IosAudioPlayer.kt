package com.matttax.reado.feature.reading.domain

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.matttax.reado.feature.reading.domain.model.PlaybackPosition
import com.matttax.reado.feature.reading.domain.model.PlaylistItem
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

  private val _position = MutableValue(PlaybackPosition.EMPTY)
  override val position: Value<PlaybackPosition> = _position

  private val _isPlayerStarted = MutableValue(false)
  override val isPlayerStarted: Value<Boolean> = _isPlayerStarted

  private var player: AVQueuePlayer? = null
  private var items: List<AVPlayerItem> = emptyList()
  private var playlistItems: List<PlaylistItem> = emptyList()
  private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
  private var positionJob: Job? = null

  override fun setPlaylist(items: List<PlaylistItem>) {
    release()
    playlistItems = items
    rebuildPlayer()
  }

  override fun playPause() {
    val current = player ?: return
    if (current.rate != 0.0f) {
      current.pause()
      _isPlaying.value = false
      stopPositionUpdates()
    } else {
      if (current.currentItem == null && playlistItems.isNotEmpty()) {
        rebuildPlayer()
      }
      val active = player ?: return
      active.play()
      _isPlaying.value = true
      if (!_isPlayerStarted.value) {
        _isPlayerStarted.value = true
      }
      startPositionUpdates()
    }
  }

  override fun release() {
    stopPositionUpdates()
    player?.pause()
    player = null
    items = emptyList()
    playlistItems = emptyList()
    _isPlayerStarted.value = false
    _isPlaying.value = false
    _position.value = PlaybackPosition.EMPTY
  }

  private fun rebuildPlayer() {
    val avItems = playlistItems.mapNotNull { item ->
      val url = NSURL.URLWithString(item.url) ?: return@mapNotNull null
      val asset = AVURLAsset(
        uRL = url,
        options = mapOf<Any?, Any>("AVURLAssetHTTPHeaderFieldsKey" to item.headers),
      )
      AVPlayerItem(asset = asset)
    }
    items = avItems
    player?.pause()
    player = AVQueuePlayer(items = avItems)
  }

  @OptIn(ExperimentalForeignApi::class)
  private fun startPositionUpdates() {
    positionJob?.cancel()
    positionJob = scope.launch {
      while (isActive) {
        val p = player ?: break
        if (_isPlayerStarted.value && p.currentItem == null) {
          _isPlayerStarted.value = false
          _isPlaying.value = false
          _position.value = PlaybackPosition.EMPTY
          break
        }
        _position.value = if (_isPlayerStarted.value) {
          val current = p.currentItem
          val idx = if (current != null) items.indexOf(current).coerceAtLeast(0) else 0
          val seconds = current?.currentTime()?.let { CMTimeGetSeconds(it) } ?: 0.0
          val ms = if (seconds.isFinite()) (seconds * 1000.0).toLong() else 0L
          PlaybackPosition(itemIndex = idx, positionMs = ms)
        } else {
          PlaybackPosition.EMPTY
        }
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
