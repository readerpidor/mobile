package com.matttax.reado.data.model.get_part_by_anchor

import kotlinx.serialization.Serializable

@Serializable
data class GetPartByAnchorRequest(
  val articleId: String,
  val anchor: Int,
)
