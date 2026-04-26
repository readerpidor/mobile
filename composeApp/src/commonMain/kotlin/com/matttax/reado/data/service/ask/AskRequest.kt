package com.matttax.reado.data.service.ask

import com.matttax.reado.data.network.serializers.Base64ByteArraySerializer
import kotlinx.serialization.Serializable

@Serializable
data class AskRequest(
  val articleId: String,
  @Serializable(with = Base64ByteArraySerializer::class)
  val audio: ByteArray,
  val currentAnchor: Int,
  val text: String,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is AskRequest) return false
    return articleId == other.articleId &&
      audio.contentEquals(other.audio) &&
      currentAnchor == other.currentAnchor &&
      text == other.text
  }

  override fun hashCode(): Int {
    var result = articleId.hashCode()
    result = 31 * result + audio.contentHashCode()
    result = 31 * result + currentAnchor
    result = 31 * result + text.hashCode()
    return result
  }
}
