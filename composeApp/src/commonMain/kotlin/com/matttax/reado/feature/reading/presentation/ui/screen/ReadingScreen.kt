package com.matttax.reado.feature.reading.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.data.model.process.ProcessResult
import com.matttax.reado.feature.reading.domain.model.ArticleMetadata
import com.matttax.reado.feature.reading.presentation.ReadingState
import com.matttax.reado.feature.reading.presentation.ui.ArticleBody
import com.matttax.reado.feature.reading.presentation.ui.ArticleHeader
import com.matttax.reado.feature.reading.presentation.ui.FloatingAiBar
import com.matttax.reado.feature.reading.presentation.ui.ReadingTopAppBar
import kotlin.math.max
import kotlinx.datetime.LocalDate

@Composable
fun ReadingScreen(
  state: ReadingState,
  isPlaying: Boolean,
  currentAnchor: Int,
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
  onPlayPauseClick: () -> Unit = {},
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(ReadingBg),
  ) {
    when (state) {
      ReadingState.Loading -> LoadingContent()
      ReadingState.Error -> ErrorContent()
      is ReadingState.Success -> ArticleContent(
        result = state.result,
        textChunks = state.textChunks,
        isPlaying = isPlaying,
        currentAnchor = currentAnchor,
        onPlayPauseClick = onPlayPauseClick,
      )
    }
    ReadingTopAppBar(
      onBack = onBack,
      progress = if (state is ReadingState.Success) 0.35f else 0f,
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(),
    )
  }
}

@Composable
private fun BoxScope.LoadingContent() {
  CircularProgressIndicator(
    color = HeaderPrimary,
    modifier = Modifier.align(Alignment.Center),
  )
}

@Composable
private fun BoxScope.ErrorContent() {
  Text(
    text = "Something went wrong. Please try again.",
    color = BodyPrimary,
    textAlign = TextAlign.Center,
    style = TextStyle(
      fontFamily = FontFamily.Serif,
      fontWeight = FontWeight.Normal,
      fontSize = 20.sp,
      lineHeight = 28.sp,
    ),
    modifier = Modifier
      .align(Alignment.Center)
      .padding(horizontal = 32.dp),
  )
}

@Composable
private fun BoxScope.ArticleContent(
  result: ProcessResult,
  textChunks: Map<Int, String>,
  currentAnchor: Int,
  isPlaying: Boolean,
  onPlayPauseClick: () -> Unit,
) {
  val scrollState = rememberScrollState()
  val lastEndMs = result.audioParts.lastOrNull()?.timings?.lastOrNull()?.endMs ?: 0L
  val readMinutes = max(1, ((lastEndMs + 59_999L) / 60_000L).toInt())
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .windowInsetsPadding(WindowInsets.statusBars)
      .padding(top = 80.dp, bottom = 140.dp)
      .padding(horizontal = 24.dp),
    verticalArrangement = Arrangement.spacedBy(48.dp),
  ) {
    ArticleHeader(
      metadata = ArticleMetadata(
        articleTopic = "Unknown",
        title = result.title,
        readMinutes = readMinutes,
        authorName = "Unknown",
        publicationDate = LocalDate(2025, 5, 2),
      ),
      isPlaying = isPlaying,
      onPlayPauseClick = onPlayPauseClick,
    )
    ArticleBody(
      textChunks = textChunks,
      currentAnchor = currentAnchor,
    )
  }
  FloatingAiBar(
    modifier = Modifier
      .align(Alignment.BottomCenter)
      .fillMaxWidth()
      .padding(horizontal = 24.dp, vertical = 32.dp),
  )
}

@Composable
@Preview
private fun ReadingScreenPreview() {
  ReadingScreen(state = ReadingState.Loading, isPlaying = false, currentAnchor = -1)
}
