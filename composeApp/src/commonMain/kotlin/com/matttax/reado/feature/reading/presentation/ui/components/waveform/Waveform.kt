package com.matttax.reado.feature.reading.presentation.ui.components.waveform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformPrimary
import com.matttax.reado.feature.reading.presentation.ui.screen.WaveformSecondary
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.EdgeResponse
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.LoudnessApproachPerMs
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.LoudnessHoldMaxMs
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.LoudnessHoldMinMs
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.LoudnessMax
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.LoudnessMin
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.MinScale
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.PulseAmplitude
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.PulsePeriodMs
import com.matttax.reado.feature.reading.presentation.ui.components.waveform.WaveformSpecs.PulsePhaseOffsetMs
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin
import kotlin.random.Random

private val Waveform =
  WaveformSpec(
    WaveBarUiSpec(
      height = 8.dp,
      color = WaveformSecondary,
    ),
    WaveBarUiSpec(
      height = 16.dp,
      color = WaveformPrimary
    ),
    WaveBarUiSpec(
      height = 24.dp,
      color = WaveformSecondary
    ),
    WaveBarUiSpec(
      height = 12.dp,
      color = WaveformPrimary
    ),
    WaveBarUiSpec(
      height = 20.dp,
      color = WaveformSecondary
    ),
  )

@Composable
internal fun Waveform(isPlaying: Boolean) {
  var phaseMs by remember { mutableStateOf(0f) }
  var loudness by remember { mutableStateOf(LoudnessMin) }
  var loudnessTarget by remember { mutableStateOf(LoudnessMin) }
  var nextRetargetMs by remember { mutableStateOf(0f) }
  LaunchedEffect(isPlaying) {
    if (!isPlaying) return@LaunchedEffect
    var lastFrameNanos = withFrameNanos { it }
    while (true) {
      val now = withFrameNanos { it }
      val dt = (now - lastFrameNanos) / 1_000_000f
      lastFrameNanos = now
      phaseMs += dt
      if (phaseMs >= nextRetargetMs) {
        loudnessTarget = LoudnessMin + Random.nextFloat() * (LoudnessMax - LoudnessMin)
        nextRetargetMs = phaseMs + LoudnessHoldMinMs +
          Random.nextFloat() * (LoudnessHoldMaxMs - LoudnessHoldMinMs)
      }
      val approach = (LoudnessApproachPerMs * dt).coerceAtMost(1f)
      loudness += (loudnessTarget - loudness) * approach
    }
  }
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    modifier = Modifier.height(24.dp),
  ) {
    Waveform.waveBars.forEachIndexed { index, spec ->
      val scale = computeBarScale(phaseMs, loudness, index)
      WaveBar(
        height = spec.height * scale,
        color = spec.color,
      )
    }
  }
}

private fun computeBarScale(phaseMs: Float, loudness: Float, index: Int): Float {
  val centerIndex = (Waveform.size - 1) / 2f
  val distFromCenter = abs(index - centerIndex) / centerIndex
  val centerWeight = 1f - (1f - EdgeResponse) * distFromCenter
  val pulse = sin((phaseMs + index * PulsePhaseOffsetMs) / PulsePeriodMs * 2f * PI.toFloat()) * PulseAmplitude
  val base = MinScale + (1f - MinScale) * loudness * centerWeight
  return (base + pulse).coerceIn(MinScale, 1f)
}
