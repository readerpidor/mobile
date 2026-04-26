package com.matttax.reado.data.model.get_next_parts

import kotlinx.serialization.Serializable

@Serializable
data class GetNextPartsRequest(
  val articleId: String,
  val lastPartIndex: Int,
)
