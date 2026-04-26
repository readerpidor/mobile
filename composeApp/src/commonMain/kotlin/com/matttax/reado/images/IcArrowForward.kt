package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcArrowForward: ImageVector
  get() = ImageVector.Builder(
    name = "ic_arrow_forward",
    defaultWidth = 16.dp,
    defaultHeight = 16.dp,
    viewportWidth = 16f,
    viewportHeight = 16f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF454652)),
    ) {
      moveTo(12.175f, 9f)
      horizontalLineTo(0f)
      verticalLineTo(7f)
      horizontalLineTo(12.175f)
      lineTo(6.575f, 1.4f)
      lineTo(8f, 0f)
      lineTo(16f, 8f)
      lineTo(8f, 16f)
      lineTo(6.575f, 14.6f)
      lineTo(12.175f, 9f)
      close()
    }
  }.build()
