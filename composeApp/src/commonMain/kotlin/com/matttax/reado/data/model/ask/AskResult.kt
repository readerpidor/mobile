package com.matttax.reado.data.model.ask

import kotlinx.serialization.Serializable

@Serializable
data class AskResult(
  val newAnchor: Int,
  val answerAudioUrl: String?,
  val answerText: String?,
)
