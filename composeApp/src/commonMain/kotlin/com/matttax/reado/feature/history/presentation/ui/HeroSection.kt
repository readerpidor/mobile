package com.matttax.reado.feature.history.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.history.presentation.ui.screen.EyebrowText
import com.matttax.reado.feature.history.presentation.ui.screen.HeroAccent
import com.matttax.reado.feature.history.presentation.ui.screen.HeroText

@Composable
internal fun HeroSection() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Text(
      text = "CHRONICLE",
      color = EyebrowText,
      style = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 15.sp,
        letterSpacing = 2.sp,
      ),
    )
    Text(
      text = buildAnnotatedString {
        append("Your\n")
        append("intellectual\n")
        withStyle(
          SpanStyle(
            fontStyle = FontStyle.Italic,
          )
        ) {
          append("journey.")
        }
      },
      color = HeroText,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        lineHeight = 60.sp,
      ),
    )
    Spacer(Modifier.height(16.dp))
    Box(
      modifier = Modifier
        .size(width = 96.dp, height = 4.dp)
        .clip(RoundedCornerShape(999.dp))
        .background(HeroAccent.copy(alpha = 0.3f)),
    )
  }
}
