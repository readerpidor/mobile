package com.matttax.reado.data.service.get_qa_history

data class GetQAHistoryError(
  val code: GetQAHistoryErrorCode,
  val description: String,
)

enum class GetQAHistoryErrorCode {
  UNSPECIFIED,
  ARTICLE_NOT_FOUND,
  FORBIDDEN,
  UNAUTHENTICATED,
  INTERNAL,
}
