package com.matttax.reado.feature.reading.presentation.ui.waveform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun WaveBar(height: Dp, color: Color) {
  Box(
    modifier = Modifier
      .size(width = 4.dp, height = height)
      .clip(CircleShape)
      .background(color),
  )
}
