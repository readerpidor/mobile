package com.matttax.reado.feature.history.presentation.ui

internal data class HistoryArticle(
    val date: String,
    val title: String,
    val progress: Float,
)

internal val SampleHistory = listOf(
  HistoryArticle(
    date = "OCTOBER 24, 2023",
    title = "The Convergence of Neural Networks and Classical Philosophy",
    progress = 1.00f,
  ),
  HistoryArticle(
    date = "OCTOBER 22, 2023",
    title = "Digital Scarcity in the Age of Abundance",
    progress = 0.45f,
  ),
  HistoryArticle(
    date = "OCTOBER 19, 2023",
    title = "The 15-Minute City: Myth or Metamorphosis?",
    progress = 0.80f,
  ),
  HistoryArticle(
    date = "OCTOBER 15, 2023",
    title = "Bauhaus and the Minimalist Digital Future",
    progress = 0.20f,
  ),
)

class HistoryArticleRef internal constructor(val title: String)
