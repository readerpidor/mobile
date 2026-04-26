package com.matttax.reado.data.model.get_next_parts

import com.matttax.reado.data.model.common.AudioPart
import kotlinx.serialization.Serializable

@Serializable
data class GetNextPartsResult(
  val audioParts: List<AudioPart>,
  val isLastBatch: Boolean,
)
