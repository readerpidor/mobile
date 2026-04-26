package com.matttax.reado.data.service.get_part_by_anchor

data class GetPartByAnchorError(
  val code: GetPartByAnchorErrorCode,
  val description: String,
)

enum class GetPartByAnchorErrorCode {
  UNSPECIFIED,
  ARTICLE_NOT_FOUND,
  ANCHOR_OUT_OF_RANGE,
  UNAUTHENTICATED,
  INTERNAL,
}
