package com.matttax.reado.feature.login.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.login.presentation.ui.screen.BodyText
import org.jetbrains.compose.resources.stringResource
import reado.composeapp.generated.resources.Res
import reado.composeapp.generated.resources.login_welcome_body

@Composable
internal fun WelcomeHeader() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Spacer(
      modifier = Modifier.height(36.dp)
    )
    Text(
      text = stringResource(Res.string.login_welcome_body),
      color = BodyText,
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
      ),
    )
  }
}
