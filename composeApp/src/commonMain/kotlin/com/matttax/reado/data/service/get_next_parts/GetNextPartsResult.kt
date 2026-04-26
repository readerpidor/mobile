package com.matttax.reado.data.service.get_next_parts

import com.matttax.reado.data.service.common.AudioPart
import kotlinx.serialization.Serializable

@Serializable
data class GetNextPartsResult(
  val audioParts: List<AudioPart>,
  val isLastBatch: Boolean,
)
