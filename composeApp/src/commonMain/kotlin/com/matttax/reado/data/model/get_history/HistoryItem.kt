package com.matttax.reado.data.model.get_history

import com.matttax.reado.data.model.common.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class HistoryItem(
  val articleId: String,
  val title: String,
  val snippet: String?,
  val progressPercent: Float,
  val startedAt: Timestamp,
)
