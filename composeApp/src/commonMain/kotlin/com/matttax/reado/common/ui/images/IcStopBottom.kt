package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcStopBottom: ImageVector
  get() = ImageVector.Builder(
    name = "ic_stop_bottom",
    defaultWidth = 11.6667.dp,
    defaultHeight = 11.6667.dp,
    viewportWidth = 28f,
    viewportHeight = 28f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF454652)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(22f, 21f)
      curveTo(22f, 21.553f, 21.553f, 22f, 21f, 22f)
      lineTo(7f, 22f)
      curveTo(6.447f, 22f, 6f, 21.553f, 6f, 21f)
      lineTo(6f, 7f)
      curveTo(6f, 6.448f, 6.447f, 6f, 7f, 6f)
      lineTo(21f, 6f)
      curveTo(21.553f, 6f, 22f, 6.448f, 22f, 7f)
      lineTo(22f, 21f)
      close()

      moveTo(26f, 0f)
      lineTo(2f, 0f)
      curveTo(0.896f, 0f, 0f, 0.896f, 0f, 2f)
      lineTo(0f, 26f)
      curveTo(0f, 27.104f, 0.896f, 28f, 2f, 28f)
      lineTo(26f, 28f)
      curveTo(27.104f, 28f, 28f, 27.104f, 28f, 26f)
      lineTo(28f, 2f)
      curveTo(28f, 0.896f, 27.104f, 0f, 26f, 0f)
      close()
    }
  }.build()
