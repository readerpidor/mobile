package com.matttax.reado.data.model.process

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProcessError(
  val code: ProcessErrorCode,
  val description: String,
)

@Serializable
enum class ProcessErrorCode {
  @SerialName("PROCESS_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("PROCESS_ERROR_CODE_INVALID_URL") INVALID_URL,
  @SerialName("PROCESS_ERROR_CODE_URL_UNREACHABLE") URL_UNREACHABLE,
  @SerialName("PROCESS_ERROR_CODE_CONTENT_EXTRACTION_FAILED") CONTENT_EXTRACTION_FAILED,
  @SerialName("PROCESS_ERROR_CODE_UNSUPPORTED_CONTENT") UNSUPPORTED_CONTENT,
  @SerialName("PROCESS_ERROR_CODE_AUDIO_GENERATION_FAILED") AUDIO_GENERATION_FAILED,
  @SerialName("PROCESS_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("PROCESS_ERROR_CODE_RATE_LIMITED") RATE_LIMITED,
  @SerialName("PROCESS_ERROR_CODE_INTERNAL") INTERNAL,
}
