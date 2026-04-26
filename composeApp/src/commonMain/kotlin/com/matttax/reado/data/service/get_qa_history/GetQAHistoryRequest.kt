package com.matttax.reado.data.service.get_qa_history

import kotlinx.serialization.Serializable

@Serializable
data class GetQAHistoryRequest(
  val articleId: String,
)
