package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcUserAvatarCircle: ImageVector
  get() = ImageVector.Builder(
    name = "circle_user",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
  ).apply {
    path(
      fill = SolidColor(Color.Transparent),
      stroke = SolidColor(Color.Black),
      strokeLineWidth = 2f,
      strokeLineCap = StrokeCap.Round,
      strokeLineJoin = StrokeJoin.Round
    ) {
      moveTo(22f, 12f)
      arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 12f, y1 = 22f)
      arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 2f, y1 = 12f)
      arcTo(10f, 10f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 22f, y1 = 12f)
      close()
    }
    path(
      fill = SolidColor(Color.Transparent),
      stroke = SolidColor(Color.Black),
      strokeLineWidth = 2f,
      strokeLineCap = StrokeCap.Round,
      strokeLineJoin = StrokeJoin.Round
    ) {
      moveTo(15f, 10f)
      arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 12f, y1 = 13f)
      arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 9f, y1 = 10f)
      arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, x1 = 15f, y1 = 10f)
      close()
    }
    path(
      fill = SolidColor(Color.Transparent),
      stroke = SolidColor(Color.Black),
      strokeLineWidth = 2f,
      strokeLineCap = StrokeCap.Round,
      strokeLineJoin = StrokeJoin.Round
    ) {
      moveTo(7f, 20.662f)
      verticalLineTo(19f)
      arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 2f, dy1 = -2f)
      horizontalLineToRelative(6f)
      arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, dx1 = 2f, dy1 = 2f)
      verticalLineToRelative(1.662f)
    }
  }.build()
