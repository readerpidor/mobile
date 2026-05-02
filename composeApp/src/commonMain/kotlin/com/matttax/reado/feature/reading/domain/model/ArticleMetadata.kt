package com.matttax.reado.feature.reading.domain.model

import kotlinx.datetime.LocalDate

data class ArticleMetadata(
  val articleTopic: String,
  val title: String,
  val readMinutes: Int,
  val authorName: String,
  val publicationDate: LocalDate,
)
