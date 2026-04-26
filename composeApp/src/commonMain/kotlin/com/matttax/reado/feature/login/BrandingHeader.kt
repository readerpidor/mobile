package com.matttax.reado.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import reado.composeapp.generated.resources.Res
import reado.composeapp.generated.resources.app_name
import reado.composeapp.generated.resources.branding_pattern

@Composable
internal fun BrandingHeader() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(137.dp)
      .background(BrandIndigo),
  ) {
    Image(
      painter = painterResource(Res.drawable.branding_pattern),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      alpha = 0.2f,
      modifier = Modifier.fillMaxSize(),
    )
    Text(
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(
          start = 32.dp,
          bottom = 14.dp
        ),
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontSize = 48.sp,
        lineHeight = 48.sp,
        letterSpacing = (-1.2).sp,
      ),
      color = BrandTitle,
      text = stringResource(Res.string.app_name),
    )
  }
}
