package com.matttax.reado.data.service.ask

data class AskError(
  val code: AskErrorCode,
  val description: String,
)

enum class AskErrorCode {
  UNSPECIFIED,
  UNAUTHENTICATED,
  ARTICLE_NOT_FOUND,
  INVALID_AUDIO,
  INVALID_ANCHOR,
  TRANSCRIPTION_FAILED,
  CHAT_FAILED,
  AUDIO_GENERATION_FAILED,
  INTERNAL,
}
