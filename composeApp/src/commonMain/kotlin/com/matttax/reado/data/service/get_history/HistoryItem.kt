package com.matttax.reado.data.service.get_history

import com.matttax.reado.data.service.common.Timestamp

data class HistoryItem(
  val articleId: String,
  val title: String,
  val snippet: String?,
  val progressPercent: Float,
  val startedAt: Timestamp,
)
