package com.matttax.reado.data.service.get_next_parts

data class GetNextPartsRequest(
  val articleId: String,
  val lastPartIndex: Int,
)
