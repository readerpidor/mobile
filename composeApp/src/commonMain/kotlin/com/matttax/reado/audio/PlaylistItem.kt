package com.matttax.reado.audio

data class PlaylistItem(
  val url: String,
  val headers: Map<String, String> = emptyMap(),
)
