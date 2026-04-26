package com.matttax.reado

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.matttax.reado.feature.account.presentation.ui.screen.AccountScreen
import com.matttax.reado.feature.history.presentation.ui.screen.HistoryScreen
import com.matttax.reado.feature.home.presentation.ui.screen.HomeScreen
import com.matttax.reado.feature.login.presentation.ui.screen.LoginScreen
import com.matttax.reado.feature.reading.presentation.ui.screen.ReadingScreen
import com.matttax.reado.navigation.RootComponent

@Composable
fun App(component: RootComponent) {
  MaterialTheme {
    Children(stack = component.stack) { child ->
    when (val instance = child.instance) {
        is RootComponent.Child.Login -> LoginScreen(
        onSignInWithGoogle = instance.component::onSignInWithGoogle,
      )
        is RootComponent.Child.Account -> AccountScreen(
        onBack = instance.component::onBack,
        onCurrentPlan = instance.component::onCurrentPlan,
      )
        is RootComponent.Child.Home -> HomeScreen(
        onProfileClick = instance.component::onProfileClick,
        onHistoryClick = instance.component::onHistoryClick,
        onSubmit = { instance.component.onSubmit() },
      )
        is RootComponent.Child.History -> HistoryScreen(
        onBack = instance.component::onBack,
      )
        is RootComponent.Child.Reading -> ReadingScreen(
        onBack = instance.component::onBack,
        )
      }
    }
  }
}
