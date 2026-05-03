package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.ChunkActive

internal fun LazyListScope.articleBody(
  textChunks: Map<Int, String>,
  currentAnchor: Int,
) {
  items(
    items = textChunks.keys.sorted(),
    key = { it },
  ) { anchor ->
    textChunks[anchor]?.let { chunk ->
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = chunk,
        color = if (anchor == currentAnchor) ChunkActive else BodyPrimary,
        style = TextStyle(
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          fontSize = 18.sp,
          lineHeight = 32.4.sp,
        ),
      )
    }
  }
}
