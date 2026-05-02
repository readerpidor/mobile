package com.matttax.reado.audio

import androidx.media3.common.PlaybackException
import androidx.media3.common.Player

internal class AudioPlaybackStateListener(
  private val onPlayingChanged: (Boolean) -> Unit,
  private val onEnded: () -> Unit,
) : Player.Listener {

  override fun onIsPlayingChanged(playing: Boolean) {
    onPlayingChanged(playing)
  }

  override fun onPlayerError(error: PlaybackException) {
    onPlayingChanged(false)
  }

  override fun onPlaybackStateChanged(state: Int) {
    when (state) {
      Player.STATE_ENDED -> onEnded()
      else -> { }
    }
  }
}
