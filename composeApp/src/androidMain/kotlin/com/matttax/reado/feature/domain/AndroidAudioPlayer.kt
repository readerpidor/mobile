package com.matttax.reado.feature.domain

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
import com.matttax.reado.feature.domain.audio.AudioPlaybackLogger
import com.matttax.reado.feature.domain.audio.AudioPlaybackStateListener
import com.matttax.reado.feature.reading.domain.AudioPlayer
import com.matttax.reado.feature.reading.domain.model.PlaybackPosition
import com.matttax.reado.feature.reading.domain.model.PlaylistItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class AndroidAudioPlayer(
  private val context: Context,
) : AudioPlayer {

  private val _isPlaying = MutableValue(false)
  override val isPlaying: Value<Boolean> = _isPlaying

  private val _position = MutableValue(PlaybackPosition.EMPTY)
  override val position: Value<PlaybackPosition> = _position

  private val _isPlayerStarted = MutableValue(false)
  override val isPlayerStarted: Value<Boolean> = _isPlayerStarted

  private var player: ExoPlayer? = null
  private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
  private var positionJob: Job? = null

  @OptIn(UnstableApi::class)
  override fun setPlaylist(items: List<PlaylistItem>) {
    release()
    if (items.isEmpty()) return
    val headers = items.firstOrNull()?.headers.orEmpty()
    val mediaItems = items.map { MediaItem.fromUri(it.url) }
    Log.i(TAG, "setPlaylist: ${items.size} items, headers=$headers")
    player = ExoPlayer.Builder(context)
      .setMediaSourceFactory(getSourceFactory(headers))
      .build()
      .apply {
        setMediaItems(mediaItems)
        addListener(AudioPlaybackLogger())
        addListener(
          AudioPlaybackStateListener(
            onPlayingChanged = { playing ->
              _isPlaying.value = playing
              if (playing) startPositionUpdates() else stopPositionUpdates()
            },
            onEnded = {
              _isPlayerStarted.value = false
              _isPlaying.value = false
              _position.value = PlaybackPosition.EMPTY
              stopPositionUpdates()
            },
          ),
        )
        prepare()
      }
  }

  override fun appendItems(items: List<PlaylistItem>) {
    if (items.isEmpty()) return
    val mediaItems = items.map { MediaItem.fromUri(it.url) }
    player?.addMediaItems(mediaItems)
    Log.i(TAG, "appendItems: ${items.size} items")
  }

  override fun playPause() {
    val playerInstance = player ?: return
    if (playerInstance.isPlaying) {
      playerInstance.pause()
    } else {
      if (!_isPlayerStarted.value) {
        playerInstance.seekTo(0, 0L)
        _isPlayerStarted.value = true
      }
      playerInstance.play()
    }
    Log.i(TAG, "playPause: isPlaying=${playerInstance.isPlaying} state=${playerInstance.playbackState}")
  }

  override fun release() {
    stopPositionUpdates()
    player?.release()
    player = null
    _isPlayerStarted.value = false
    _isPlaying.value = false
    _position.value = PlaybackPosition.EMPTY
  }

  private fun startPositionUpdates() {
    positionJob?.cancel()
    positionJob = scope.launch {
      while (isActive) {
        player?.let { playerInstance ->
          _position.value = if (_isPlayerStarted.value) {
            PlaybackPosition(
              itemIndex = playerInstance.currentMediaItemIndex,
              positionMs = playerInstance.currentPosition,
            )
          } else {
            PlaybackPosition.EMPTY
          }
          delay(POLL_INTERVAL_MS)
        }
      }
    }
  }

  private fun stopPositionUpdates() {
    positionJob?.cancel()
    positionJob = null
    player?.let { playerInstance ->
      _position.value =
        PlaybackPosition(playerInstance.currentMediaItemIndex, playerInstance.currentPosition)
    }
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
    const val POLL_INTERVAL_MS = 50L
  }
}
