package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IconsMore: ImageVector
  get() = ImageVector.Builder(
    name = "more-vertical",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
  ).apply {
    path(
      fill = SolidColor(Color.Black),
    ) {
      moveTo(12f, 8f)
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = -4f
      )
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = 4f
      )
      close()
      moveTo(12f, 14f)
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = -4f
      )
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = 4f
      )
      close()
      moveTo(12f, 20f)
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = -4f
      )
      arcToRelative(2f, 2f, 0f,
        isMoreThanHalf = true,
        isPositiveArc = true,
        dx1 = 0f,
        dy1 = 4f
      )
      close()
    }
  }.build()
