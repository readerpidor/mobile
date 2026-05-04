package com.matttax.reado.feature.reading.domain

import com.arkivanov.decompose.value.Value
import com.matttax.reado.feature.reading.domain.model.PlaybackPosition
import com.matttax.reado.feature.reading.domain.model.PlaylistItem

interface AudioPlayer {
  val isPlaying: Value<Boolean>
  val isPlayerStarted: Value<Boolean>
  val position: Value<PlaybackPosition>
  fun setPlaylist(items: List<PlaylistItem>)
  fun appendItems(items: List<PlaylistItem>)
  fun playPause()
  fun release()
}
