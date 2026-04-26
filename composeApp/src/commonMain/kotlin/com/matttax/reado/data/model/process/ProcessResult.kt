package com.matttax.reado.data.model.process

import com.matttax.reado.data.model.common.AudioPart
import kotlinx.serialization.Serializable

@Serializable
data class ProcessResult(
  val articleId: String,
  val title: String,
  val articleUrl: String,
  val audioParts: List<AudioPart>,
  val totalParts: Int,
)
