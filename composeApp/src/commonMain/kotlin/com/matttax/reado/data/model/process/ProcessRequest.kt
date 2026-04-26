package com.matttax.reado.data.model.process

import kotlinx.serialization.Serializable

@Serializable
data class ProcessRequest(
  val url: String,
)
