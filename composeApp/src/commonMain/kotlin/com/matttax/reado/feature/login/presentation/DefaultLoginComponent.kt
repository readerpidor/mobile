package com.matttax.reado.feature.login.presentation

import com.arkivanov.decompose.ComponentContext
import com.matttax.reado.navigation.components.LoginComponent

class DefaultLoginComponent(
  componentContext: ComponentContext,
  private val onSignedIn: () -> Unit,
) : LoginComponent, ComponentContext by componentContext {

  override fun onSignInWithGoogle() {
    onSignedIn()
  }
}
