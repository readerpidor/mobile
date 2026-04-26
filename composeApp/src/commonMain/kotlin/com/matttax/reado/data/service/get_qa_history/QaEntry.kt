package com.matttax.reado.data.service.get_qa_history

data class QaEntry(
  val entryId: String,
  val question: String,
  val answer: String,
  val askedAt: com.matttax.reado.data.service.common.Timestamp,
)
