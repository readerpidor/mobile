package com.matttax.reado.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import reado.composeapp.generated.resources.Res
import reado.composeapp.generated.resources.login_privacy_policy
import reado.composeapp.generated.resources.login_terms_of_service

@Composable
internal fun FooterLinks(
  onTermsClick: () -> Unit,
  onPrivacyClick: () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        horizontal = 12.dp,
        vertical = 24.dp,
      ),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = stringResource(Res.string.login_terms_of_service),
      color = FooterText.copy(alpha = 0.5f),
      style = linkStyle,
      textAlign = TextAlign.Start,
      modifier = Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onTermsClick,
      ),
    )
    Text(
      text = stringResource(Res.string.login_privacy_policy),
      color = FooterText.copy(alpha = 0.5f),
      style = linkStyle,
      textAlign = TextAlign.End,
      modifier = Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onPrivacyClick,
      ),
    )
  }
}

private val linkStyle = TextStyle(
  fontFamily = FontFamily.SansSerif,
  fontWeight = FontWeight.Bold,
  fontSize = 12.sp,
  lineHeight = 16.sp,
)
