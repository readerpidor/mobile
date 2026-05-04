package com.matttax.reado.feature.reading.domain.model

data class PlaylistItem(
  val url: String,
  val headers: Map<String, String> = emptyMap(),
)
