package com.matttax.reado.data.service.process

import com.matttax.reado.data.service.common.AudioPart
import kotlinx.serialization.Serializable

@Serializable
data class ProcessResult(
  val articleId: String,
  val title: String,
  val articleUrl: String,
  val audioParts: List<AudioPart>,
  val totalParts: Int,
)
