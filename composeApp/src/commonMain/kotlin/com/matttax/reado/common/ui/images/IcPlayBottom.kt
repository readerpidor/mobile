package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcPlayBottom: ImageVector
  get() = ImageVector.Builder(
    name = "ic_play_bottom",
    defaultWidth = 11.6667.dp,
    defaultHeight = 11.6667.dp,
    viewportWidth = 11.6667f,
    viewportHeight = 11.6667f,
  ).apply {
    path(
      stroke = SolidColor(Color(0xFF454652)),
      strokeLineWidth = 2f,
      strokeLineJoin = StrokeJoin.Round,
    ) {
      moveTo(8.4213f, 4.2790f)
      curveTo(9.2447f, 4.7944f, 9.6568f, 5.0521f, 9.7979f, 5.3823f)
      curveTo(9.9214f, 5.6702f, 9.9214f, 5.9964f, 9.7979f, 6.2846f)
      curveTo(9.6568f, 6.6147f, 9.2447f, 6.8722f, 8.4213f, 7.3877f)
      lineTo(4.5476f, 9.8086f)
      curveTo(3.6321f, 10.3807f, 3.1746f, 10.6663f, 2.7969f, 10.6359f)
      curveTo(2.4677f, 10.6094f, 2.1660f, 10.4422f, 1.9691f, 10.1775f)
      curveTo(1.7434f, 9.8731f, 1.7434f, 9.3336f, 1.7434f, 8.2541f)
      verticalLineTo(3.4124f)
      curveTo(1.7434f, 2.3331f, 1.7434f, 1.7934f, 1.9691f, 1.4892f)
      curveTo(2.1660f, 1.2241f, 2.4677f, 1.0568f, 2.7969f, 1.0304f)
      curveTo(3.1746f, 1.0f, 3.6321f, 1.2860f, 4.5476f, 1.8581f)
      lineTo(8.4213f, 4.2790f)
      close()
    }
  }.build()
