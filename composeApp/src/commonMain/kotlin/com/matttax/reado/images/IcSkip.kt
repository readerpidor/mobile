package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcSkip: ImageVector
  get() = ImageVector.Builder(
    name = "ic_skip",
    defaultWidth = 10.8333.dp,
    defaultHeight = 10.dp,
    viewportWidth = 10.8333f,
    viewportHeight = 10f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF454652)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(9.16667f, 10f)
      verticalLineTo(0f)
      horizontalLineTo(10.8333f)
      verticalLineTo(10f)
      horizontalLineTo(9.16667f)
      close()

      moveTo(0f, 10f)
      verticalLineTo(0f)
      lineTo(7.5f, 5f)
      lineTo(0f, 10f)
      close()

      moveTo(1.66667f, 6.875f)
      lineTo(4.5f, 5f)
      lineTo(1.66667f, 3.125f)
      verticalLineTo(6.875f)
      close()
    }
  }.build()
