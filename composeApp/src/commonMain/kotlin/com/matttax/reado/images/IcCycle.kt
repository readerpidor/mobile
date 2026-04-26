package com.matttax.reado.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcCycle: ImageVector
  get() = ImageVector.Builder(
    name = "ic_cycle",
    defaultWidth = 22.5.dp,
    defaultHeight = 22.5.dp,
    viewportWidth = 22.5f,
    viewportHeight = 22.5f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF4C1A00)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(11.25f, 22.5f)
      curveTo(8.375f, 22.5f, 5.86979f, 21.5469f, 3.73438f, 19.6406f)
      curveTo(1.59896f, 17.7344f, 0.375f, 15.3542f, 0.0625f, 12.5f)
      horizontalLineTo(2.625f)
      curveTo(2.91667f, 14.6667f, 3.88021f, 16.4583f, 5.51562f, 17.875f)
      curveTo(7.15104f, 19.2917f, 9.0625f, 20f, 11.25f, 20f)
      curveTo(13.6875f, 20f, 15.7552f, 19.151f, 17.4531f, 17.4531f)
      curveTo(19.151f, 15.7552f, 20f, 13.6875f, 20f, 11.25f)
      curveTo(20f, 8.8125f, 19.151f, 6.74479f, 17.4531f, 5.04688f)
      curveTo(15.7552f, 3.34896f, 13.6875f, 2.5f, 11.25f, 2.5f)
      curveTo(9.8125f, 2.5f, 8.46875f, 2.83333f, 7.21875f, 3.5f)
      curveTo(5.96875f, 4.16667f, 4.91667f, 5.08333f, 4.0625f, 6.25f)
      horizontalLineTo(7.5f)
      verticalLineTo(8.75f)
      horizontalLineTo(0f)
      verticalLineTo(1.25f)
      horizontalLineTo(2.5f)
      verticalLineTo(4.1875f)
      curveTo(3.5625f, 2.85417f, 4.85938f, 1.82292f, 6.39062f, 1.09375f)
      curveTo(7.92188f, 0.364583f, 9.54167f, 0f, 11.25f, 0f)
      curveTo(12.8125f, 0f, 14.276f, 0.296875f, 15.6406f, 0.890625f)
      curveTo(17.0052f, 1.48438f, 18.1927f, 2.28646f, 19.2031f, 3.29688f)
      curveTo(20.2135f, 4.30729f, 21.0156f, 5.49479f, 21.6094f, 6.85938f)
      curveTo(22.2031f, 8.22396f, 22.5f, 9.6875f, 22.5f, 11.25f)
      curveTo(22.5f, 12.8125f, 22.2031f, 14.276f, 21.6094f, 15.6406f)
      curveTo(21.0156f, 17.0052f, 20.2135f, 18.1927f, 19.2031f, 19.2031f)
      curveTo(18.1927f, 20.2135f, 17.0052f, 21.0156f, 15.6406f, 21.6094f)
      curveTo(14.276f, 22.2031f, 12.8125f, 22.5f, 11.25f, 22.5f)
      close()

      moveTo(14.75f, 16.5f)
      lineTo(10f, 11.75f)
      verticalLineTo(5f)
      horizontalLineTo(12.5f)
      verticalLineTo(10.75f)
      lineTo(16.5f, 14.75f)
      lineTo(14.75f, 16.5f)
      close()
    }
  }.build()
