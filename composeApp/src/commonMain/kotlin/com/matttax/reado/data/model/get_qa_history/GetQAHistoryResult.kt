package com.matttax.reado.data.model.get_qa_history

import kotlinx.serialization.Serializable

@Serializable
data class GetQAHistoryResult(
  val entries: List<QaEntry>,
)
