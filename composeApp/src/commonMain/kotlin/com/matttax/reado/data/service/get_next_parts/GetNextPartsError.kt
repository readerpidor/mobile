package com.matttax.reado.data.service.get_next_parts

data class GetNextPartsError(
  val code: GetNextPartsErrorCode,
  val description: String,
)

enum class GetNextPartsErrorCode {
  UNSPECIFIED,
  ARTICLE_NOT_FOUND,
  FORBIDDEN,
  INVALID_LAST_PART_INDEX,
  NOT_READY,
  UNAUTHENTICATED,
  INTERNAL,
}
