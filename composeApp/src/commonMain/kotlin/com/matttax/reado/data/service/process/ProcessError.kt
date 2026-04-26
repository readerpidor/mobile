package com.matttax.reado.data.service.process

data class ProcessError(
  val code: ProcessErrorCode,
  val description: String,
)

enum class ProcessErrorCode {
  UNSPECIFIED,
  INVALID_URL,
  URL_UNREACHABLE,
  CONTENT_EXTRACTION_FAILED,
  UNSUPPORTED_CONTENT,
  AUDIO_GENERATION_FAILED,
  UNAUTHENTICATED,
  RATE_LIMITED,
  INTERNAL,
}
