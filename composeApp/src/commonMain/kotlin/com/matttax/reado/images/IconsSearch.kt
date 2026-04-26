package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconsSearch: ImageVector
  get() = ImageVector.Builder(
    name = "search",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
  ).apply {
    path(
      fill = SolidColor(Color.Transparent),
      stroke = SolidColor(Color.Black),
      strokeLineWidth = 2f,
      strokeLineCap = StrokeCap.Round,
      strokeLineJoin = StrokeJoin.Round,
    ) {
      moveTo(11f, 11f)
      moveToRelative(-7f, 0f)
      arcToRelative(7f, 7f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 14f,
        dy1 = 0f
      )
      arcToRelative(7f, 7f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = -14f,
        dy1 = 0f
      )
      close()
      moveTo(21f, 21f)
      lineTo(16.65f, 16.65f)
    }
  }.build()
