package com.matttax.reado.feature.history.presentation

import com.arkivanov.decompose.ComponentContext
import com.matttax.reado.navigation.components.HistoryComponent

class DefaultHistoryComponent(
  componentContext: ComponentContext,
  private val onBack: () -> Unit,
) : HistoryComponent, ComponentContext by componentContext {

  override fun onBack() {
    onBack.invoke()
  }
}