package com.matttax.reado.feature.reading.presentation

import com.arkivanov.decompose.ComponentContext
import com.matttax.reado.navigation.components.ReadingComponent

class DefaultReadingComponent(
  componentContext: ComponentContext,
  private val onBack: () -> Unit,
) : ReadingComponent, ComponentContext by componentContext {

  override fun onBack() {
    onBack.invoke()
  }
}