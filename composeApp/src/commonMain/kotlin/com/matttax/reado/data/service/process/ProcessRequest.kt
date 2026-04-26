package com.matttax.reado.data.service.process

import kotlinx.serialization.Serializable

@Serializable
data class ProcessRequest(
  val url: String,
)
