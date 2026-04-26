package com.matttax.reado.feature.login.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matttax.reado.feature.login.presentation.ui.BrandingHeader
import com.matttax.reado.feature.login.presentation.ui.LoginCanvas

@Composable
fun LoginScreen(
  modifier: Modifier = Modifier,
  onSignInWithGoogle: () -> Unit = {},
  onTermsClick: () -> Unit = {},
  onPrivacyClick: () -> Unit = {},
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(color = CanvasBg)
  ) {
    BrandingHeader()
    LoginCanvas(
      modifier = Modifier.fillMaxSize(),
      onSignInWithGoogle = onSignInWithGoogle,
      onTermsClick = onTermsClick,
      onPrivacyClick = onPrivacyClick,
    )
  }
}

@Composable
@Preview
private fun LoginScreenPreview() {
  LoginScreen()
}
