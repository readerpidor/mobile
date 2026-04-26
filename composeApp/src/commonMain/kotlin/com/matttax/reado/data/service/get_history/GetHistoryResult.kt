package com.matttax.reado.data.service.get_history

import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryResult(
  val items: List<HistoryItem>,
)
