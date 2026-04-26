package com.matttax.reado.data.service.ask

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskError(
  val code: AskErrorCode,
  val description: String,
)

@Serializable
enum class AskErrorCode {
  @SerialName("ASK_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("ASK_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("ASK_ERROR_CODE_ARTICLE_NOT_FOUND") ARTICLE_NOT_FOUND,
  @SerialName("ASK_ERROR_CODE_INVALID_AUDIO") INVALID_AUDIO,
  @SerialName("ASK_ERROR_CODE_INVALID_ANCHOR") INVALID_ANCHOR,
  @SerialName("ASK_ERROR_CODE_TRANSCRIPTION_FAILED") TRANSCRIPTION_FAILED,
  @SerialName("ASK_ERROR_CODE_CHAT_FAILED") CHAT_FAILED,
  @SerialName("ASK_ERROR_CODE_AUDIO_GENERATION_FAILED") AUDIO_GENERATION_FAILED,
  @SerialName("ASK_ERROR_CODE_INTERNAL") INTERNAL,
}
