package com.matttax.reado.feature.reading.domain.model

data class PlaybackPosition(
  val itemIndex: Int,
  val positionMs: Long,
) {
  companion object {
    val EMPTY = PlaybackPosition(-1, -1L)
  }
}
