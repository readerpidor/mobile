package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcPause: ImageVector
  get() = ImageVector.Builder(
    name = "ic_pause",
    defaultWidth = 11.dp,
    defaultHeight = 14.dp,
    viewportWidth = 11f,
    viewportHeight = 14f,
  ).apply {
    path(fill = SolidColor(Color.White)) {
      moveTo(0f, 0f)
      horizontalLineTo(3f)
      verticalLineTo(14f)
      horizontalLineTo(0f)
      close()
    }
    path(fill = SolidColor(Color.White)) {
      moveTo(8f, 0f)
      horizontalLineTo(11f)
      verticalLineTo(14f)
      horizontalLineTo(8f)
      close()
    }
  }.build()
