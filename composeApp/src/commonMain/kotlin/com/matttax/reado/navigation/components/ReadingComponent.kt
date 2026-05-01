package com.matttax.reado.navigation.components

import com.arkivanov.decompose.value.Value
import com.matttax.reado.feature.reading.presentation.ReadingState

interface ReadingComponent {
  val state: Value<ReadingState>
  fun onBack()
}
