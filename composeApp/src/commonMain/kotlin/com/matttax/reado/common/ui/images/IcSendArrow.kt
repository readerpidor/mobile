package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcSendArrow: ImageVector
  get() = ImageVector.Builder(
    name = "ic_send_arrow",
    defaultWidth = 10.325.dp,
    defaultHeight = 10.325.dp,
    viewportWidth = 10.325f,
    viewportHeight = 10.325f,
  ).apply {
    path(
      fill = SolidColor(Color.White),
    ) {
      moveTo(6.79583f, 6.08125f)
      horizontalLineTo(0f)
      verticalLineTo(4.24375f)
      horizontalLineTo(6.79583f)
      lineTo(3.85f, 1.29792f)
      lineTo(5.1625f, 0f)
      lineTo(10.325f, 5.1625f)
      lineTo(5.1625f, 10.325f)
      lineTo(3.85f, 9.02708f)
      lineTo(6.79583f, 6.08125f)
      close()
    }
  }.build()
