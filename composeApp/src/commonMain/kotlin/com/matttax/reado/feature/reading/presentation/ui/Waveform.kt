package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformSecondary

@Composable
internal fun Waveform() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    modifier = Modifier.height(24.dp),
  ) {
    WaveBar(
      height = 8.dp,
      color = WaveformSecondary
    )
    WaveBar(
      height = 16.dp,
      color = WaveformPrimary
    )
    WaveBar(
      height = 24.dp,
      color = WaveformSecondary
    )
    WaveBar(
      height = 12.dp,
      color = WaveformPrimary
    )
    WaveBar(
      height = 20.dp,
      color = WaveformSecondary
    )
  }
}
