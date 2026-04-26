package com.matttax.reado.data.service.get_history

data class GetHistoryError(
  val code: GetHistoryErrorCode,
  val description: String,
)

enum class GetHistoryErrorCode {
  UNSPECIFIED,
  UNAUTHENTICATED,
  INTERNAL,
}
