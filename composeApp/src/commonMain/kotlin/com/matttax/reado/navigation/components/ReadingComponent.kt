package com.matttax.reado.navigation.components

import com.arkivanov.decompose.value.Value
import com.matttax.reado.feature.reading.presentation.ReadingState

interface ReadingComponent {
  val state: Value<ReadingState>
  val isPlaying: Value<Boolean>
  val currentAnchor: Value<Int>
  fun onPlayPauseClick()
  fun onBack()
}
