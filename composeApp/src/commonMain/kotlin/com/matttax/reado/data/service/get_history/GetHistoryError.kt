package com.matttax.reado.data.service.get_history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryError(
  val code: GetHistoryErrorCode,
  val description: String,
)

@Serializable
enum class GetHistoryErrorCode {
  @SerialName("GET_HISTORY_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("GET_HISTORY_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("GET_HISTORY_ERROR_CODE_INTERNAL") INTERNAL,
}
