package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.HighlightBlockBg
import com.matttax.reado.feature.reading.presentation.ui.screen.HighlightSpanBg
import com.matttax.reado.feature.reading.presentation.ui.screen.HighlightUnderline

@Composable
internal fun AiHighlightBlock() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(HighlightBlockBg)
      .padding(horizontal = 24.dp, vertical = 16.dp),
  ) {
    Text(
      text = buildAnnotatedString {
        append("To stand beneath the cantilevered planes of the Boston City Hall or within the ribbed vaults of the Barbican Estate is to experience ")
        withStyle(
          SpanStyle(
            background = HighlightSpanBg,
            textDecoration = TextDecoration.Underline,
            color = HighlightUnderline,
          )
        ) {
          append("architectural sublime. The scale is often humbling, forcing the observer to confront their own transience against the seeming permanence of the structure.")
        }
      },
      color = BodyPrimary,
      style = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 32.4.sp,
      ),
    )
  }
}
