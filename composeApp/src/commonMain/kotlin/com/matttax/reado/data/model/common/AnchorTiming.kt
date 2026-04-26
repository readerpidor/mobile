package com.matttax.reado.data.model.common

import kotlinx.serialization.Serializable

@Serializable
data class AnchorTiming(
  val anchor: Int,
  val startMs: Long,
  val endMs: Long,
)
