package com.matttax.reado.feature.home.presentation

import com.arkivanov.decompose.ComponentContext
import com.matttax.reado.navigation.components.HomeComponent

class DefaultHomeComponent(
  componentContext: ComponentContext,
  private val onProfileClick: () -> Unit,
  private val onHistoryClick: () -> Unit,
  private val onSubmit: (String) -> Unit,
) : HomeComponent, ComponentContext by componentContext {

  override fun onProfileClick() {
    onProfileClick.invoke()
  }

  override fun onHistoryClick() {
    onHistoryClick.invoke()
  }

  override fun onSubmit(url: String) {
    onSubmit.invoke(url)
  }
}
