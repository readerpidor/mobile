package com.matttax.reado.audio

data class PlaybackPosition(
  val itemIndex: Int,
  val positionMs: Long,
) {
  companion object {
    val EMPTY = PlaybackPosition(-1, -1L)
  }
}
