package com.matttax.reado.data.service.common

import com.matttax.reado.data.network.serializers.TimestampSerializer
import kotlinx.serialization.Serializable

@Serializable(with = TimestampSerializer::class)
data class Timestamp(
  val seconds: Long,
  val nanos: Int,
)
