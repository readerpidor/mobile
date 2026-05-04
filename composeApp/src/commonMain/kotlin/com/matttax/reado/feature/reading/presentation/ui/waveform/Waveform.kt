package com.matttax.reado.feature.reading.presentation.ui.waveform

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
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.EdgeResponse
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.LoudnessApproachPerMs
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.LoudnessHoldMaxMs
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.LoudnessHoldMinMs
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.LoudnessMax
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.LoudnessMin
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.MinScale
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.PulseAmplitude
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.PulsePeriodMs
import com.matttax.reado.feature.reading.presentation.ui.waveform.WaveformSpecs.PulsePhaseOffsetMs
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin
import kotlin.random.Random

private val Waveform = WaveformSpec(
  WaveBarUiSpec(8.dp, WaveformSecondary),
  WaveBarUiSpec(16.dp, WaveformPrimary),
  WaveBarUiSpec(24.dp, WaveformSecondary),
  WaveBarUiSpec(12.dp, WaveformPrimary),
  WaveBarUiSpec(20.dp, WaveformSecondary),
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
