package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcSpeaker: ImageVector
  get() = ImageVector.Builder(
    name = "ic_speaker",
    defaultWidth = 11.6667.dp,
    defaultHeight = 11.6667.dp,
    viewportWidth = 11.6667f,
    viewportHeight = 11.6667f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF454652)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(6.66667f, 11.6667f)
      verticalLineTo(0f)
      horizontalLineTo(11.6667f)
      verticalLineTo(11.6667f)
      horizontalLineTo(6.66667f)
      close()

      moveTo(0f, 11.6667f)
      verticalLineTo(0f)
      horizontalLineTo(5f)
      verticalLineTo(11.6667f)
      horizontalLineTo(0f)
      close()

      moveTo(8.33333f, 10f)
      horizontalLineTo(10f)
      verticalLineTo(1.66667f)
      horizontalLineTo(8.33333f)
      verticalLineTo(10f)
      close()

      moveTo(1.66667f, 10f)
      horizontalLineTo(3.33333f)
      verticalLineTo(1.66667f)
      horizontalLineTo(1.66667f)
      verticalLineTo(10f)
      close()
    }
  }.build()
