package com.matttax.reado.feature.reading.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.matttax.reado.data.ReaderService
import com.matttax.reado.data.model.Response
import com.matttax.reado.data.model.common.AudioPart
import com.matttax.reado.data.model.get_next_parts.GetNextPartsRequest
import com.matttax.reado.data.model.get_next_parts.GetNextPartsResult
import com.matttax.reado.data.model.process.ProcessRequest
import com.matttax.reado.data.model.process.ProcessResult
import com.matttax.reado.data.network.STORAGE_REACHABLE_AUTHORITY
import com.matttax.reado.data.network.STORAGE_SIGNED_AUTHORITY
import com.matttax.reado.feature.reading.domain.AudioPlayer
import com.matttax.reado.feature.reading.domain.model.PlaybackPosition
import com.matttax.reado.feature.reading.domain.model.PlaylistItem
import com.matttax.reado.navigation.components.ReadingComponent
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DefaultReadingComponent(
  componentContext: ComponentContext,
  private val readerService: ReaderService,
  private val audioPlayer: AudioPlayer,
  private val url: String,
  private val onBack: () -> Unit,
) : ReadingComponent, ComponentContext by componentContext {

  private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

  private val _state = MutableValue<ReadingState>(ReadingState.Loading)
  override val state: Value<ReadingState> = _state
  override val isPlaying: Value<Boolean> = audioPlayer.isPlaying
  override val isPlayerStarted: Value<Boolean> = audioPlayer.isPlayerStarted

  private val _currentAnchor = MutableValue(NO_ANCHOR)
  override val currentAnchor: Value<Int> = _currentAnchor

  private var processResult: ProcessResult? = null
  private var isLastBatch: Boolean = false
  private var isLoadingNext: Boolean = false

  init {
    val positionSubscription = audioPlayer.position.subscribe(::onPositionChanged)
    lifecycle.doOnDestroy {
      positionSubscription.cancel()
      audioPlayer.release()
      scope.cancel()
    }
    load()
  }

  private fun onPositionChanged(position: PlaybackPosition) {
    if (position == PlaybackPosition.EMPTY) {
      _currentAnchor.value = NO_ANCHOR
      return
    }
    loadNextPartsIfNeeded(position.itemIndex)
    updateAnchor(position)
  }

  private fun updateAnchor(position: PlaybackPosition) {
    val parts = processResult?.audioParts ?: emptyList()
    val partIndex = position.itemIndex
    val part = parts.getOrNull(partIndex)
    if (part == null) {
      _currentAnchor.value = NO_ANCHOR
      return
    }
    val timings = part.timings
    val isLastPart = partIndex == parts.lastIndex
    val pos = position.positionMs
    val anchor = timings.indices.firstNotNullOfOrNull { i ->
      val timing = timings[i]
      val isLastTiming = i == timings.lastIndex
      val matches = when {
        !isLastTiming -> pos in timing.startMs until timings[i + 1].startMs
        !isLastPart -> pos >= timing.startMs
        else -> pos in timing.startMs..timing.endMs
      }
      if (matches) timing.anchor else null
    } ?: NO_ANCHOR
    _currentAnchor.value = anchor
  }

  override fun onBack() {
    onBack.invoke()
  }

  override fun onPlayPauseClick() {
    audioPlayer.playPause()
  }

  override fun onStopClick() {
    audioPlayer.endPlayback()
  }

  private fun load() {
    scope.launch {
      _state.value = ReadingState.Loading
      _state.value = try {
        when (val response = readerService.process(ProcessRequest(url = url))) {
          is Response.Success -> {
            val result = response.result
            processResult = result
            isLastBatch = result.audioParts.size >= result.totalParts
            val text = readerService.fetchArticleText(result.articleUrl)
            val textChunks = processText(text)
            audioPlayer.setPlaylist(result.audioParts.map(::toPlaylistItem))
            ReadingState.Success(result, textChunks)
          }
          is Response.Error -> ReadingState.Error
        }
      } catch (_: Exception) {
        ReadingState.Error
      }
    }
  }

  private fun loadNextPartsIfNeeded(itemIndex: Int) {
    if (isLastBatch || isLoadingNext) return
    val current = processResult ?: return
    val parts = current.audioParts
    if (parts.isEmpty()) return
    if (current.totalParts > 0 && parts.size >= current.totalParts) return
    if (itemIndex < parts.lastIndex) return
    val lastPartIndex = parts.last().partIndex
    isLoadingNext = true
    scope.launch {
      try {
        val request = GetNextPartsRequest(articleId = current.articleId, lastPartIndex = lastPartIndex)
        when (val response = readerService.getNextParts(request)) {
          is Response.Success -> appendBatch(response.result)
          is Response.Error -> Unit
        }
      } catch (_: Exception) {
        // ignore; will retry on next position update
      } finally {
        isLoadingNext = false
      }
    }
  }

  private suspend fun appendBatch(result: GetNextPartsResult) {
    val current = processResult
    if (current != null && result.audioParts.isNotEmpty()) {
      val updated = current.copy(audioParts = current.audioParts + result.audioParts)
      processResult = updated
      audioPlayer.appendItems(result.audioParts.map(::toPlaylistItem))
      val text = readerService.fetchArticleText(updated.articleUrl)
      val textChunks = processText(text)
      if (_state.value is ReadingState.Success) {
        _state.value = ReadingState.Success(updated, textChunks)
      }
    }
    if (result.isLastBatch) {
      isLastBatch = true
    }
  }

  private fun toPlaylistItem(part: AudioPart) =
    PlaylistItem(
      url = part.audioUrl.replace(STORAGE_SIGNED_AUTHORITY, STORAGE_REACHABLE_AUTHORITY),
      headers = mapOf(HttpHeaders.Host to STORAGE_SIGNED_AUTHORITY),
    )

  private fun processText(text: String): Map<Int, String> {
    val matches = Regex("<<(\\d+)>>").findAll(text).toList()
    return buildMap {
      matches.forEachIndexed { i, m ->
        val anchor = m.groupValues[1].toInt()
        val start = m.range.last + 1
        val end = matches.getOrNull(i + 1)?.range?.first ?: text.length
        val value = text.substring(start, end).trim()
        if (value.isNotEmpty()) put(anchor, value)
      }
    }
  }


  companion object {
    const val NO_ANCHOR = -1
  }
}
