package com.matttax.reado.feature.history.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matttax.reado.feature.history.presentation.ui.HeroSection
import com.matttax.reado.feature.history.presentation.ui.HistoryArticleRef
import com.matttax.reado.feature.history.presentation.ui.HistoryList
import com.matttax.reado.feature.history.presentation.ui.HistoryTopAppBar

@Composable
fun HistoryScreen(
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
  onViewQA: (HistoryArticleRef) -> Unit = {},
) {
  val scrollState = rememberScrollState()
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(HistoryBg),
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .windowInsetsPadding(WindowInsets.statusBars)
        .padding(top = 64.dp, bottom = 48.dp)
        .padding(horizontal = 24.dp)
        .widthIn(max = 768.dp),
      verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
      HeroSection()
      HistoryList(onViewQA = onViewQA)
    }
    HistoryTopAppBar(
      onBack = onBack,
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(),
    )
  }
}

@Composable
@Preview
private fun HistoryScreenPreview() {
  HistoryScreen()
}
