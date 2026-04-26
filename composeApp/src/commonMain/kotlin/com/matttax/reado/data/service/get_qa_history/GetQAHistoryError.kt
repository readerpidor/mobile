package com.matttax.reado.data.service.get_qa_history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetQAHistoryError(
  val code: GetQAHistoryErrorCode,
  val description: String,
)

@Serializable
enum class GetQAHistoryErrorCode {
  @SerialName("GET_QA_HISTORY_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("GET_QA_HISTORY_ERROR_CODE_ARTICLE_NOT_FOUND") ARTICLE_NOT_FOUND,
  @SerialName("GET_QA_HISTORY_ERROR_CODE_FORBIDDEN") FORBIDDEN,
  @SerialName("GET_QA_HISTORY_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("GET_QA_HISTORY_ERROR_CODE_INTERNAL") INTERNAL,
}
