package com.matttax.reado.feature.reading.presentation.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matttax.reado.data.model.process.ProcessResult
import com.matttax.reado.feature.reading.domain.model.ArticleMetadata
import com.matttax.reado.feature.reading.presentation.ReadingState
import com.matttax.reado.feature.reading.presentation.ui.components.header.ArticleHeader
import com.matttax.reado.feature.reading.presentation.ui.components.footer.FloatingAiBar
import com.matttax.reado.feature.reading.presentation.ui.components.top_bar.ReadingTopAppBar
import com.matttax.reado.feature.reading.presentation.ui.components.body.articleBody
import com.matttax.reado.feature.reading.presentation.ui.screen.ScreenLayoutSpecs.BottomThresholdFraction
import com.matttax.reado.feature.reading.presentation.ui.screen.ScreenLayoutSpecs.ItemSpacingDp
import com.matttax.reado.feature.reading.presentation.ui.screen.ScreenLayoutSpecs.LeadItemsCount
import kotlinx.datetime.LocalDate
import kotlin.math.max

@Composable
fun ReadingScreen(
  state: ReadingState,
  isPlaying: Boolean,
  isPlayerStarted: Boolean,
  currentAnchor: Int,
  modifier: Modifier = Modifier,
  onBack: () -> Unit = {},
  onPlayPauseClick: () -> Unit = {},
) {
  val density = LocalDensity.current
  var topBarHeight by remember { mutableStateOf(0.dp) }
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
        isPlayerStarted = isPlayerStarted,
        currentAnchor = currentAnchor,
        topPadding = topBarHeight,
        onPlayPauseClick = onPlayPauseClick,
      )
    }
    ReadingTopAppBar(
      onBack = onBack,
      progress = if (state is ReadingState.Success) 0.35f else 0f,
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth()
        .onSizeChanged { size ->
          topBarHeight = with(density) { size.height.toDp() }
        },
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
  isPlayerStarted: Boolean,
  topPadding: Dp,
  onPlayPauseClick: () -> Unit,
) {
  val density = LocalDensity.current
  val lazyListState = rememberLazyListState()
  val lastEndMs = result.audioParts.lastOrNull()?.timings?.lastOrNull()?.endMs ?: 0L
  val readMinutes = max(1, ((lastEndMs + 59_999L) / 60_000L).toInt())
  val sortedAnchors = remember(textChunks) { textChunks.keys.sorted() }

  LazyColumn(
    state = lazyListState,
    modifier = Modifier
      .fillMaxSize()
      .padding(top = topPadding)
      .padding(horizontal = 24.dp),
    verticalArrangement = Arrangement.spacedBy(ItemSpacingDp.dp),
  ) {
    item {
      Spacer(
        modifier = Modifier.height(10.dp),
      )
    }
    item(key = "header") {
      Box(modifier = Modifier.padding(bottom = 32.dp)) {
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
      }
    }
    articleBody(
      textChunks = textChunks,
      currentAnchor = currentAnchor,
    )
    item {
      Spacer(
        modifier = Modifier.height(100.dp),
      )
    }
  }

  LaunchedEffect(currentAnchor, sortedAnchors) {
    if (currentAnchor < 0) return@LaunchedEffect
    val chunkIdx = sortedAnchors.indexOf(currentAnchor)
    if (chunkIdx < 0) return@LaunchedEffect
    val itemIndex = chunkIdx + LeadItemsCount

    val info = lazyListState.layoutInfo
    val viewportHeight = info.viewportEndOffset - info.viewportStartOffset
    if (viewportHeight <= 0) return@LaunchedEffect
    val threshold = info.viewportStartOffset + (viewportHeight * BottomThresholdFraction).toInt()

    val visible = info.visibleItemsInfo.firstOrNull { it.index == itemIndex }
    if (visible != null) {
      val itemBottom = visible.offset + visible.size
      if (itemBottom > threshold) {
        lazyListState.animateScrollBy((itemBottom - threshold).toFloat())
      }
      return@LaunchedEffect
    }

    val firstVisibleIdx = info.visibleItemsInfo.firstOrNull()?.index
    if (firstVisibleIdx != null && itemIndex < firstVisibleIdx) return@LaunchedEffect

    val lastVisible = info.visibleItemsInfo.lastOrNull()
    if (lastVisible == null) {
      lazyListState.animateScrollToItem(itemIndex)
      return@LaunchedEffect
    }
    val spacingPx = with(density) { ItemSpacingDp.dp.roundToPx() }
    val chunkSizes = info.visibleItemsInfo
      .filter { it.index >= LeadItemsCount }
      .map { it.size }
    val estimatedChunkHeight =
      if (chunkSizes.isNotEmpty()) chunkSizes.average().toInt() else lastVisible.size
    val itemsBetween = itemIndex - lastVisible.index
    val estimatedTargetBottom = lastVisible.offset + lastVisible.size +
      itemsBetween * (estimatedChunkHeight + spacingPx)
    val delta = estimatedTargetBottom - threshold
    if (delta > 0) {
      lazyListState.animateScrollBy(delta.toFloat())
    }
  }

  AnimatedVisibility(
    visible = isPlayerStarted,
    enter = slideInVertically(initialOffsetY = { it }),
    exit = slideOutVertically(targetOffsetY = { it }),
    modifier = Modifier.align(Alignment.BottomCenter),
  ) {
    FloatingAiBar(
      isPlaying = isPlaying,
      onPlayPauseClick = onPlayPauseClick,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 32.dp),
    )
  }
}

@Composable
@Preview
private fun ReadingScreenPreview() {
  ReadingScreen(state = ReadingState.Loading, isPlaying = false, isPlayerStarted = false, currentAnchor = -1)
}
