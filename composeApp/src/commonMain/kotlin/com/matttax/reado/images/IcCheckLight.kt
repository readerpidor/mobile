package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcCheckLight: ImageVector
  get() = ImageVector.Builder(
    name = "ic_check_light",
    defaultWidth = 16.6667.dp,
    defaultHeight = 16.6667.dp,
    viewportWidth = 16.6667f,
    viewportHeight = 16.6667f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFFF79261)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(7.16667f, 12.1667f)
      lineTo(13.0417f, 6.29167f)
      lineTo(11.875f, 5.125f)
      lineTo(7.16667f, 9.83333f)
      lineTo(4.79167f, 7.45833f)
      lineTo(3.625f, 8.625f)
      lineTo(7.16667f, 12.1667f)
      close()

      moveTo(8.33333f, 16.6667f)
      curveTo(7.18056f, 16.6667f, 6.09722f, 16.4479f, 5.08333f, 16.0104f)
      curveTo(4.06944f, 15.5729f, 3.1875f, 14.9792f, 2.4375f, 14.2292f)
      curveTo(1.6875f, 13.4792f, 1.09375f, 12.5972f, 0.65625f, 11.5833f)
      curveTo(0.21875f, 10.5694f, 0f, 9.48611f, 0f, 8.33333f)
      curveTo(0f, 7.18056f, 0.21875f, 6.09722f, 0.65625f, 5.08333f)
      curveTo(1.09375f, 4.06944f, 1.6875f, 3.1875f, 2.4375f, 2.4375f)
      curveTo(3.1875f, 1.6875f, 4.06944f, 1.09375f, 5.08333f, 0.65625f)
      curveTo(6.09722f, 0.21875f, 7.18056f, 0f, 8.33333f, 0f)
      curveTo(9.48611f, 0f, 10.5694f, 0.21875f, 11.5833f, 0.65625f)
      curveTo(12.5972f, 1.09375f, 13.4792f, 1.6875f, 14.2292f, 2.4375f)
      curveTo(14.9792f, 3.1875f, 15.5729f, 4.06944f, 16.0104f, 5.08333f)
      curveTo(16.4479f, 6.09722f, 16.6667f, 7.18056f, 16.6667f, 8.33333f)
      curveTo(16.6667f, 9.48611f, 16.4479f, 10.5694f, 16.0104f, 11.5833f)
      curveTo(15.5729f, 12.5972f, 14.9792f, 13.4792f, 14.2292f, 14.2292f)
      curveTo(13.4792f, 14.9792f, 12.5972f, 15.5729f, 11.5833f, 16.0104f)
      curveTo(10.5694f, 16.4479f, 9.48611f, 16.6667f, 8.33333f, 16.6667f)
      close()
    }
  }.build()
