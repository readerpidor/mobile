package com.matttax.reado.audio

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class AndroidAudioPlayer(
  private val context: Context,
) : AudioPlayer {

  private val _isPlaying = MutableValue(false)
  override val isPlaying: Value<Boolean> = _isPlaying

  private var player: ExoPlayer? = null

  @OptIn(UnstableApi::class)
  override fun setPlaylist(items: List<PlaylistItem>) {
    release()
    if (items.isEmpty()) return
    val headers = items.firstOrNull()?.headers.orEmpty()
    val mediaItems = items.map { MediaItem.fromUri(it.url) }
    Log.i(TAG, "setPlaylist: ${items.size} items, headers=${headers}")
    player = ExoPlayer.Builder(context)
      .setMediaSourceFactory(getSourceFactory(headers))
      .build()
      .apply {
        setMediaItems(mediaItems)
        addListener(AudioPlaybackLogger())
        addListener(AudioPlaybackStateListener { playing -> _isPlaying.value = playing })
        prepare()
      }
  }

  override fun playPause() {
    val playerInstance = player ?: return
    if (playerInstance.isPlaying) {
      playerInstance.pause()
    } else {
      playerInstance.play()
    }
    Log.i(TAG, "playPause: isPlaying=${playerInstance.isPlaying} state=${playerInstance.playbackState}")
  }

  override fun release() {
    player?.release()
    player = null
    _isPlaying.value = false
  }

  private fun getSourceFactory(headers: Map<String, String>) =
    DefaultMediaSourceFactory(context)
      .setDataSourceFactory(getHttpFactory(headers))

  @OptIn(UnstableApi::class)
  private fun getHttpFactory(headers: Map<String, String>) =
    DefaultHttpDataSource.Factory()
    .setAllowCrossProtocolRedirects(true)
    .apply {
      if (headers.isNotEmpty()) {
        setDefaultRequestProperties(headers)
      }
    }

  private companion object {
    const val TAG = "AndroidAudioPlayer"
  }
}
