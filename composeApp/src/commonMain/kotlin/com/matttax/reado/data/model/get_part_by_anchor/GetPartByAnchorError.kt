package com.matttax.reado.data.model.get_part_by_anchor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPartByAnchorError(
  val code: GetPartByAnchorErrorCode,
  val description: String,
)

@Serializable
enum class GetPartByAnchorErrorCode {
  @SerialName("GET_PART_BY_ANCHOR_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("GET_PART_BY_ANCHOR_ERROR_CODE_ARTICLE_NOT_FOUND") ARTICLE_NOT_FOUND,
  @SerialName("GET_PART_BY_ANCHOR_ERROR_CODE_ANCHOR_OUT_OF_RANGE") ANCHOR_OUT_OF_RANGE,
  @SerialName("GET_PART_BY_ANCHOR_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("GET_PART_BY_ANCHOR_ERROR_CODE_INTERNAL") INTERNAL,
}
