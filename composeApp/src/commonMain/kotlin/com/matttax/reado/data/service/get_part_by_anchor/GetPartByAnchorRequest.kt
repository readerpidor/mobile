package com.matttax.reado.data.service.get_part_by_anchor

import kotlinx.serialization.Serializable

@Serializable
data class GetPartByAnchorRequest(
  val articleId: String,
  val anchor: Int,
)
