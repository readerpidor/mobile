package com.matttax.reado.feature.reading.presentation.ui.body

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary

internal fun LazyListScope.articleBody(
  textChunks: Map<Int, String>,
  currentAnchor: Int,
) {
  items(
    items = textChunks.keys.sorted(),
    key = { it },
  ) { anchor ->
    textChunks[anchor]?.let { chunk ->
      val isActive = anchor == currentAnchor
      var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
      val bubbleAlpha by animateFloatAsState(
        targetValue = if (isActive) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
      )
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .drawBehind {
            if (bubbleAlpha <= 0f) return@drawBehind
            drawBubbleShape(layout, bubbleAlpha)
          },
        text = chunk,
        color = BodyPrimary,
        onTextLayout = { layout = it },
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
