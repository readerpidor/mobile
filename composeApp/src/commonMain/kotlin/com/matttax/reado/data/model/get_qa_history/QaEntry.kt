package com.matttax.reado.data.model.get_qa_history

import com.matttax.reado.data.model.common.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class QaEntry(
  val entryId: String,
  val question: String,
  val answer: String,
  val askedAt: Timestamp,
)
