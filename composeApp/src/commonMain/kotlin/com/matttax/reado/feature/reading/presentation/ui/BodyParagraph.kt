package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary

@Composable
internal fun BodyParagraph(
  text: AnnotatedString,
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = text,
    color = BodyPrimary,
    style = TextStyle(
      fontFamily = FontFamily.Serif,
      fontWeight = FontWeight.Normal,
      fontSize = 18.sp,
      lineHeight = 32.4.sp,
    ),
  )
}
