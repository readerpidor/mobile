package com.matttax.reado.feature.reading.presentation.ui.body

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.ChunkActiveBg

fun DrawScope.drawBubbleShape(layout: TextLayoutResult? = null) {
  val tl = layout ?: return
  val padH = 8.dp.toPx()

  val lineRects = buildList {
    for (i in 0 until tl.lineCount) {
      val left = tl.getLineLeft(i) - padH
      val right = if (i == tl.lineCount - 1) {
        tl.getLineRight(i) + padH
      } else {
        this@drawBubbleShape.size.width
      }
      if (right <= left) continue
      val top = tl.getLineTop(i) - 12f
      val bottom = tl.getLineBottom(i) + 12f
      if (bottom <= top) continue
      add(Rect(left, top, right, bottom))
    }
  }
  if (lineRects.isEmpty()) return

  val bodyPath = buildShape(lineRects, spread = 0f, dy = 0f)
  clipPath(path = bodyPath, clipOp = ClipOp.Difference) {
    for (layer in ChunkShadowLayers) {
      drawPath(
        path = buildShape(lineRects, layer.spreadDp.dp.toPx(), layer.dyDp.dp.toPx()),
        color = ChunkActiveBg.copy(alpha = layer.alpha),
      )
    }
  }
  drawPath(path = bodyPath, color = ChunkActiveBg)
}

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

private fun DrawScope.buildShape(lineRects: List<Rect>, spread: Float, dy: Float): Path {
  val rectsCount = lineRects.size
  val cornerPx = 10.dp.toPx()
  val convexR = cornerPx + spread
  val concaveR = (cornerPx - spread).coerceAtLeast(0f)
  val firstRect = lineRects[0]
  val lastRect = lineRects[rectsCount - 1]
  val outerLeft = firstRect.left - spread
  val outerRight = firstRect.right + spread
  val lastRight = lastRect.right + spread
  val outerTop = firstRect.top + dy - spread
  val outerBottom = lastRect.bottom + dy + spread

  val path = Path()

  if (rectsCount == 1 || lastRight >= outerRight) {
    path.addRoundRect(
      RoundRect(
        left = outerLeft,
        top = outerTop,
        right = maxOf(outerRight, lastRight),
        bottom = outerBottom,
        cornerRadius = CornerRadius(convexR),
      )
    )
    return path
  }

  val stepY = lineRects[rectsCount - 2].bottom + dy + spread
  path.moveTo(outerLeft + convexR, outerTop)
  path.lineTo(outerRight - convexR, outerTop)
  path.arcTo(
    rect = Rect(outerRight - 2 * convexR, outerTop, outerRight, outerTop + 2 * convexR),
    startAngleDegrees = 270f,
    sweepAngleDegrees = 90f,
    forceMoveTo = false,
  )
  path.lineTo(outerRight, stepY - convexR)
  path.arcTo(
    rect = Rect(outerRight - 2 * convexR, stepY - 2 * convexR, outerRight, stepY),
    startAngleDegrees = 0f,
    sweepAngleDegrees = 90f,
    forceMoveTo = false,
  )
  path.lineTo(lastRight + concaveR, stepY)
  if (concaveR > 0f) {
    path.arcTo(
      rect = Rect(lastRight, stepY, lastRight + 2 * concaveR, stepY + 2 * concaveR),
      startAngleDegrees = 270f,
      sweepAngleDegrees = -90f,
      forceMoveTo = false,
    )
  }
  path.lineTo(lastRight, outerBottom - convexR)
  path.arcTo(
    rect = Rect(lastRight - 2 * convexR, outerBottom - 2 * convexR, lastRight, outerBottom),
    startAngleDegrees = 0f,
    sweepAngleDegrees = 90f,
    forceMoveTo = false,
  )
  path.lineTo(outerLeft + convexR, outerBottom)
  path.arcTo(
    rect = Rect(outerLeft, outerBottom - 2 * convexR, outerLeft + 2 * convexR, outerBottom),
    startAngleDegrees = 90f,
    sweepAngleDegrees = 90f,
    forceMoveTo = false,
  )
  path.lineTo(outerLeft, outerTop + convexR)
  path.arcTo(
    rect = Rect(outerLeft, outerTop, outerLeft + 2 * convexR, outerTop + 2 * convexR),
    startAngleDegrees = 180f,
    sweepAngleDegrees = 90f,
    forceMoveTo = false,
  )
  path.close()
  return path
}
