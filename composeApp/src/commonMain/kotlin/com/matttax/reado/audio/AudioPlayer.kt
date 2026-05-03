package com.matttax.reado.audio

import com.arkivanov.decompose.value.Value

interface AudioPlayer {
  val isPlaying: Value<Boolean>
  val isPlayerStarted: Value<Boolean>
  val position: Value<PlaybackPosition>
  fun setPlaylist(items: List<PlaylistItem>)
  fun playPause()
  fun release()
}
