package com.matttax.reado.feature.account.presentation

import com.arkivanov.decompose.ComponentContext
import com.matttax.reado.navigation.components.AccountComponent

class DefaultAccountComponent(
  componentContext: ComponentContext,
  private val onBack: () -> Unit,
  private val onCurrentPlan: () -> Unit,
) : AccountComponent, ComponentContext by componentContext {

  override fun onBack() {
    onBack.invoke()
  }

  override fun onCurrentPlan() {
    onCurrentPlan.invoke()
  }
}