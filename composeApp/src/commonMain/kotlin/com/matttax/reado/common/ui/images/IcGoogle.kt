package com.matttax.reado.common.ui.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcGoogle: ImageVector
  get() = ImageVector.Builder(
    name = "ic_google",
    defaultWidth = 20.dp,
    defaultHeight = 20.dp,
    viewportWidth = 20f,
    viewportHeight = 20f,
  ).apply {
    path(
      fill = SolidColor(Color(0xFF4285F4)),
    ) {
      moveTo(18.8f, 10.2083f)
      curveTo(18.8f, 9.55833f, 18.7417f, 8.93333f, 18.6333f, 8.33333f)
      horizontalLineTo(10f)
      verticalLineTo(11.8833f)
      horizontalLineTo(14.9333f)
      curveTo(14.7167f, 13.025f, 14.0667f, 13.9917f, 13.0917f, 14.6417f)
      verticalLineTo(16.95f)
      horizontalLineTo(16.0667f)
      curveTo(17.8f, 15.35f, 18.8f, 13f, 18.8f, 10.2083f)
      close()
    }
    path(
      fill = SolidColor(Color(0xFF34A853)),
    ) {
      moveTo(10f, 19.1667f)
      curveTo(12.475f, 19.1667f, 14.55f, 18.35f, 16.0667f, 16.95f)
      lineTo(13.0917f, 14.6417f)
      curveTo(12.275f, 15.1917f, 11.2333f, 15.525f, 10f, 15.525f)
      curveTo(7.61667f, 15.525f, 5.59167f, 13.9167f, 4.86667f, 11.75f)
      horizontalLineTo(1.81667f)
      verticalLineTo(14.1167f)
      curveTo(3.325f, 17.1083f, 6.41667f, 19.1667f, 10f, 19.1667f)
      close()
    }
    path(
      fill = SolidColor(Color(0xFFFBBC05)),
    ) {
      moveTo(4.86667f, 11.7417f)
      curveTo(4.68333f, 11.1917f, 4.575f, 10.6083f, 4.575f, 10f)
      curveTo(4.575f, 9.39167f, 4.68333f, 8.80833f, 4.86667f, 8.25833f)
      verticalLineTo(5.89167f)
      horizontalLineTo(1.81667f)
      curveTo(1.19167f, 7.125f, 0.833333f, 8.51667f, 0.833333f, 10f)
      curveTo(0.833333f, 11.4833f, 1.19167f, 12.875f, 1.81667f, 14.1083f)
      lineTo(4.86667f, 11.7417f)
      close()
    }
    path(
      fill = SolidColor(Color(0xFFEA4335)),
    ) {
      moveTo(10f, 4.48333f)
      curveTo(11.35f, 4.48333f, 12.55f, 4.95f, 13.5083f, 5.85f)
      lineTo(16.1333f, 3.225f)
      curveTo(14.5417f, 1.74167f, 12.475f, 0.833333f, 10f, 0.833333f)
      curveTo(6.41667f, 0.833333f, 3.325f, 2.89167f, 1.81667f, 5.89167f)
      lineTo(4.86667f, 8.25833f)
      curveTo(5.59167f, 6.09167f, 7.61667f, 4.48333f, 14.8667f, 4.48333f)
      horizontalLineTo(10f)
      close()
    }
  }.build()
