package com.matttax.reado.feature.reading.presentation.ui

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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.feature.reading.presentation.ui.screen.BodyPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.ChunkActiveBg

private data class ShadowLayer(val dyDp: Float, val spreadDp: Float, val alpha: Float)

private val ChunkShadowLayers: List<ShadowLayer> = run {
  val count = 16
  val maxSpreadDp = 7f
  val maxDyDp = 3f
  val perLayerAlpha = 0.014f
  (1..count).map { i ->
    val t = i.toFloat() / count
    ShadowLayer(
      dyDp = maxDyDp * t,
      spreadDp = maxSpreadDp * t,
      alpha = perLayerAlpha,
    )
  }
}

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
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .drawBehind {
            if (!isActive) return@drawBehind
            val tl = layout ?: return@drawBehind
            val padH = 8.dp.toPx()
            val cornerPx = 10.dp.toPx()

            val lineRects = buildList {
              for (i in 0 until tl.lineCount) {
                val left = tl.getLineLeft(i) - padH
                val right = tl.getLineRight(i) + padH
                if (right <= left) continue
                val top = tl.getLineTop(i)
                val bottom = tl.getLineBottom(i)
                if (bottom <= top) continue
                add(Rect(left, top, right, bottom))
              }
            }
            if (lineRects.isEmpty()) return@drawBehind

            val lastIdx = lineRects.lastIndex
            fun buildShape(spread: Float, dy: Float): Path = Path().apply {
              lineRects.forEachIndexed { idx, r ->
                val outerTopR = (if (idx == 0) cornerPx else 0f) + spread
                val outerBottomR = (if (idx == lastIdx) cornerPx else 0f) + spread
                addRoundRect(
                  RoundRect(
                    left = r.left - spread,
                    top = r.top + dy - spread,
                    right = r.right + spread,
                    bottom = r.bottom + dy + spread,
                    topLeftCornerRadius = CornerRadius(outerTopR),
                    topRightCornerRadius = CornerRadius(outerTopR),
                    bottomRightCornerRadius = CornerRadius(outerBottomR),
                    bottomLeftCornerRadius = CornerRadius(outerBottomR),
                  )
                )
              }
            }

            val bodyPath = buildShape(spread = 0f, dy = 0f)

            clipPath(path = bodyPath, clipOp = ClipOp.Difference) {
              for (layer in ChunkShadowLayers) {
                drawPath(
                  path = buildShape(layer.spreadDp.dp.toPx(), layer.dyDp.dp.toPx()),
                  color = ChunkActiveBg.copy(alpha = layer.alpha),
                )
              }
            }

            drawPath(path = bodyPath, color = ChunkActiveBg)
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
