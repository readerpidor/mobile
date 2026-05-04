package com.matttax.reado.feature.reading.presentation.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformSecondary

private data class WaveBarSpec(val height: Dp, val color: androidx.compose.ui.graphics.Color)

private val WAVE_BARS = listOf(
  WaveBarSpec(8.dp, WaveformSecondary),
  WaveBarSpec(16.dp, WaveformPrimary),
  WaveBarSpec(24.dp, WaveformSecondary),
  WaveBarSpec(12.dp, WaveformPrimary),
  WaveBarSpec(20.dp, WaveformSecondary),
)

private const val ANIM_DURATION_MS = 600
private const val ANIM_PHASE_OFFSET_MS = 120
private const val MIN_SCALE = 0.3f

@Composable
internal fun Waveform(isPlaying: Boolean) {
  val transition = rememberInfiniteTransition(label = "waveform")
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    modifier = Modifier.height(24.dp),
  ) {
    WAVE_BARS.forEachIndexed { index, spec ->
      val scale by transition.animateFloat(
        initialValue = MIN_SCALE,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
          animation = tween(durationMillis = ANIM_DURATION_MS, easing = FastOutSlowInEasing),
          repeatMode = RepeatMode.Reverse,
          initialStartOffset = StartOffset(index * ANIM_PHASE_OFFSET_MS),
        ),
        label = "wave-bar-$index",
      )
      WaveBar(
        height = if (isPlaying) spec.height * scale else spec.height,
        color = spec.color,
      )
    }
  }
}
