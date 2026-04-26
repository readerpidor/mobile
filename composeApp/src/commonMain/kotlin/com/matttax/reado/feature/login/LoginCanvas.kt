package com.matttax.reado.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun LoginCanvas(
  modifier: Modifier = Modifier,
  onSignInWithGoogle: () -> Unit,
  onTermsClick: () -> Unit,
  onPrivacyClick: () -> Unit,
) {
  Box(
    modifier = modifier
      .background(color = CanvasBg)
      .padding(24.dp),
    contentAlignment = Alignment.TopCenter,
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
      WelcomeHeader()
      GoogleSignInButton(
        onClick = onSignInWithGoogle,
      )
      Spacer(
        modifier = Modifier.weight(1f),
      )
      FooterLinks(
        onTermsClick = onTermsClick,
        onPrivacyClick = onPrivacyClick,
      )
    }
  }
}
