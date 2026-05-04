package com.matttax.reado.feature.reading.presentation.ui.waveform

class WaveformSpec(
  val waveBars: List<WaveBarUiSpec>,
) {
  val size = waveBars.size

  constructor(
    vararg waveBars: WaveBarUiSpec,
  ) : this(waveBars.toList())
}
