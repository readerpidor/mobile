package com.matttax.reado.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
internal fun BackgroundBlob(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .clip(CircleShape)
      .background(
        Brush.radialGradient(
          colors = listOf(
            BlobTint.copy(alpha = 0.35f),
            BlobTint.copy(alpha = 0.18f),
            Color.Transparent,
          ),
        )
      ),
  )
}
