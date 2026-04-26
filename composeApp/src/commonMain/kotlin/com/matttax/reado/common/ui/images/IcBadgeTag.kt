package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcBadgeTag: ImageVector
  get() = ImageVector.Builder(
    name = "ic_badge_tag",
    defaultWidth = 9.33333.dp,
    defaultHeight = 12.25.dp,
    viewportWidth = 9.33333f,
    viewportHeight = 12.25f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF351000)),
      pathFillType = PathFillType.EvenOdd,
    ) {
      moveTo(3.31042f, 6.825f)
      lineTo(3.82083f, 5.1625f)
      lineTo(2.47917f, 4.08333f)
      horizontalLineTo(4.14167f)
      lineTo(4.66667f, 2.45f)
      lineTo(5.19167f, 4.08333f)
      horizontalLineTo(6.85417f)
      lineTo(5.49792f, 5.1625f)
      lineTo(6.00833f, 6.825f)
      lineTo(4.66667f, 5.78958f)
      lineTo(3.31042f, 6.825f)
      close()

      moveTo(1.16667f, 12.25f)
      verticalLineTo(7.74375f)
      curveTo(0.797222f, 7.33542f, 0.510417f, 6.86875f, 0.30625f, 6.34375f)
      curveTo(0.102083f, 5.81875f, 0f, 5.25972f, 0f, 4.66667f)
      curveTo(0f, 3.36389f, 0.452083f, 2.26042f, 1.35625f, 1.35625f)
      curveTo(2.26042f, 0.452083f, 3.36389f, 0f, 4.66667f, 0f)
      curveTo(5.96944f, 0f, 7.07292f, 0.452083f, 7.97708f, 1.35625f)
      curveTo(8.88125f, 2.26042f, 9.33333f, 3.36389f, 9.33333f, 4.66667f)
      curveTo(9.33333f, 5.25972f, 9.23125f, 5.81875f, 9.02708f, 6.34375f)
      curveTo(8.82292f, 6.86875f, 8.53611f, 7.33542f, 8.16667f, 7.74375f)
      verticalLineTo(12.25f)
      lineTo(4.66667f, 11.0833f)
      lineTo(1.16667f, 12.25f)
      close()

      moveTo(4.66667f, 8.16667f)
      curveTo(5.63889f, 8.16667f, 6.46528f, 7.82639f, 7.14583f, 7.14583f)
      curveTo(7.82639f, 6.46528f, 8.16667f, 5.63889f, 8.16667f, 4.66667f)
      curveTo(8.16667f, 3.69444f, 7.82639f, 2.86806f, 7.14583f, 2.1875f)
      curveTo(6.46528f, 1.50694f, 5.63889f, 1.16667f, 4.66667f, 1.16667f)
      curveTo(3.69444f, 1.16667f, 2.86806f, 1.50694f, 2.1875f, 2.1875f)
      curveTo(1.50694f, 2.86806f, 1.16667f, 3.69444f, 1.16667f, 4.66667f)
      curveTo(1.16667f, 5.63889f, 1.50694f, 6.46528f, 2.1875f, 7.14583f)
      curveTo(2.86806f, 7.82639f, 3.69444f, 8.16667f, 4.66667f, 8.16667f)
      close()
    }
  }.build()
