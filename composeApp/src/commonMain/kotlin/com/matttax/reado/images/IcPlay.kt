package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcPlay: ImageVector
  get() = ImageVector.Builder(
    name = "ic_play",
    defaultWidth = 11.dp,
    defaultHeight = 14.dp,
    viewportWidth = 11f,
    viewportHeight = 14f,
  ).apply {
    path(
      fill = SolidColor(Color.White),
    ) {
      moveTo(0f, 14f)
      verticalLineTo(0f)
      lineTo(11f, 7f)
      lineTo(0f, 14f)
      close()
    }
  }.build()
