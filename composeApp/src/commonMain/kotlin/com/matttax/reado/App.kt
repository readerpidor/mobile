package com.matttax.reado

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.matttax.reado.feature.account.presentation.AccountScreen
import com.matttax.reado.feature.history.HistoryScreen
import com.matttax.reado.feature.home.HomeScreen
import com.matttax.reado.feature.login.LoginScreen
import com.matttax.reado.feature.reading.ReadingScreen

private enum class Screen { Login, Account, Home, History, Reading }

@Composable
@Preview
fun App() {
  MaterialTheme {
    var screen by remember { mutableStateOf(Screen.Home) }
    when (screen) {
      Screen.Login -> LoginScreen(
        onSignInWithGoogle = { screen = Screen.Account },
      )
      Screen.Account -> AccountScreen(
        onBack = { screen = Screen.Login },
        onCurrentPlan = { screen = Screen.Home },
      )
      Screen.Home -> HomeScreen(
        onProfileClick = { screen = Screen.Account },
        onHistoryClick = { screen = Screen.History },
        onSubmit = { screen = Screen.Reading },
      )
      Screen.History -> HistoryScreen(
        onBack = { screen = Screen.Home },
      )
      Screen.Reading -> ReadingScreen(
        onBack = { screen = Screen.Home },
      )
    }
  }
}
