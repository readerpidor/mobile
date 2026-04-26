package com.matttax.reado.data.service.get_next_parts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNextPartsError(
  val code: GetNextPartsErrorCode,
  val description: String,
)

@Serializable
enum class GetNextPartsErrorCode {
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_UNSPECIFIED") UNSPECIFIED,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_ARTICLE_NOT_FOUND") ARTICLE_NOT_FOUND,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_FORBIDDEN") FORBIDDEN,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_INVALID_LAST_PART_INDEX") INVALID_LAST_PART_INDEX,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_NOT_READY") NOT_READY,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_UNAUTHENTICATED") UNAUTHENTICATED,
  @SerialName("GET_NEXT_PARTS_ERROR_CODE_INTERNAL") INTERNAL,
}
