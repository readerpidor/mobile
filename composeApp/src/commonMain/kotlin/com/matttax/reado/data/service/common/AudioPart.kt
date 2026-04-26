package com.matttax.reado.data.service.common

data class AudioPart(
  val partIndex: Int,
  val audioUrl: String,
  val timings: List<AnchorTiming>,
  val firstAnchor: Int,
  val lastAnchor: Int,
)
