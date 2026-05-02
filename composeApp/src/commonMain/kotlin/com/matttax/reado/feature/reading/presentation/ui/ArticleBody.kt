package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.ChunkActive

@Composable
internal fun ArticleBody(textChunks: Map<Int, String>, currentAnchor: Int) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    textChunks.entries.sortedBy { it.key }.forEach { (anchor, chunk) ->
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
