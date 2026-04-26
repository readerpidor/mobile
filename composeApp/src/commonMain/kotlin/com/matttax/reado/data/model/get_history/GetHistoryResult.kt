package com.matttax.reado.data.model.get_history

import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryResult(
  val items: List<HistoryItem>,
)
