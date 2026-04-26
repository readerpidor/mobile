package com.matttax.reado.data.model.common

import kotlinx.serialization.Serializable

@Serializable
data class AudioPart(
  val partIndex: Int,
  val audioUrl: String,
  val timings: List<AnchorTiming>,
  val firstAnchor: Int,
  val lastAnchor: Int,
)
