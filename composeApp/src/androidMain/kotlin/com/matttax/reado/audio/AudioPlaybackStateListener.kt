package com.matttax.reado.audio

import androidx.media3.common.PlaybackException
import androidx.media3.common.Player

internal class AudioPlaybackStateListener(
  private val onPlayingChanged: (Boolean) -> Unit,
) : Player.Listener {

  override fun onIsPlayingChanged(playing: Boolean) {
    onPlayingChanged(playing)
  }

  override fun onPlayerError(error: PlaybackException) {
    onPlayingChanged(false)
  }
}
