package com.matttax.reado.feature.reading.presentation.ui.components.body

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.TextData
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary

internal fun LazyListScope.articleBody(
  currentAnchor: Int,
  textChunks: Map<Int, TextData>,
) {
  items(
    items = textChunks.keys.sorted(),
    key = { it },
  ) { anchor ->
    textChunks[anchor]?.let { chunk ->
      var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
      val isActive = anchor == currentAnchor
      val bubbleAlpha by animateFloatAsState(
        targetValue = if (isActive) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
      )
      val paragraphType = chunk.paragraphType()
      val fontSize = paragraphType.fontSize()
      val fontWeight = paragraphType.fontWeight()
      val verticalPadding = paragraphType.verticalPadding()
      val lineHeight = (fontSize.value * paragraphType.lineHeightMultiplier()).sp
      val annotatedText = remember(chunk) { chunk.toAnnotatedString() }
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = verticalPadding)
          .drawBehind {
            if (bubbleAlpha <= 0f) return@drawBehind
            drawBubbleShape(layout, bubbleAlpha)
          },
        text = annotatedText,
        color = BodyPrimary,
        style = TextStyle(
          fontFamily = FontFamily.Serif,
          fontWeight = fontWeight,
          lineHeight = lineHeight,
          fontSize = fontSize,
        ),
        onTextLayout = { layout = it },
      )
    }
  }
}
