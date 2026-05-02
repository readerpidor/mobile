package com.matttax.reado.audio

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVQueuePlayer
import platform.AVFoundation.AVURLAsset
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.rate
import platform.Foundation.NSURL

class IosAudioPlayer : AudioPlayer {

  private val _isPlaying = MutableValue(false)
  override val isPlaying: Value<Boolean> = _isPlaying

  private var player: AVQueuePlayer? = null

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
    player = AVQueuePlayer(items = avItems)
  }

  override fun playPause() {
    val playerInstance = player ?: return
    if (playerInstance.rate != 0.0f) {
      playerInstance.pause()
      _isPlaying.value = false
    } else {
      playerInstance.play()
      _isPlaying.value = true
    }
  }

  override fun release() {
    player?.pause()
    player = null
    _isPlaying.value = false
  }
}
