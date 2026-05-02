package com.matttax.reado.feature.reading.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.matttax.reado.audio.AudioPlayer
import com.matttax.reado.audio.PlaybackPosition
import com.matttax.reado.audio.PlaylistItem
import com.matttax.reado.data.ReaderService
import com.matttax.reado.data.model.Response
import com.matttax.reado.data.model.common.AudioPart
import com.matttax.reado.data.model.process.ProcessRequest
import com.matttax.reado.data.network.STORAGE_REACHABLE_AUTHORITY
import com.matttax.reado.data.network.STORAGE_SIGNED_AUTHORITY
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

  private val _currentAnchor = MutableValue(NO_ANCHOR)
  override val currentAnchor: Value<Int> = _currentAnchor

  private var audioParts: List<AudioPart> = emptyList()

  init {
    val positionSub = audioPlayer.position.subscribe(::updateAnchor)
    lifecycle.doOnDestroy {
      positionSub.cancel()
      audioPlayer.release()
      scope.cancel()
    }
    load()
  }

  private fun updateAnchor(position: PlaybackPosition) {
    if (position == PlaybackPosition.EMPTY) {
      _currentAnchor.value = NO_ANCHOR
      return
    }
    val partIndex = position.itemIndex
    val part = audioParts.getOrNull(partIndex)
    if (part == null) {
      _currentAnchor.value = NO_ANCHOR
      return
    }
    val timings = part.timings
    val isLastPart = partIndex == audioParts.lastIndex
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

  private fun load() {
    scope.launch {
      _state.value = ReadingState.Loading
      _state.value = try {
        when (val response = readerService.process(ProcessRequest(url = url))) {
          is Response.Success -> {
            val text = readerService.fetchArticleText(response.result.articleUrl)
            val textChunks = processText(text)
            audioParts = response.result.audioParts
            audioPlayer.setPlaylist(
              response.result.audioParts.map { part ->
                PlaylistItem(
                  url = part.audioUrl.replace(STORAGE_SIGNED_AUTHORITY, STORAGE_REACHABLE_AUTHORITY),
                  headers = mapOf(HttpHeaders.Host to STORAGE_SIGNED_AUTHORITY),
                )
              },
            )
            ReadingState.Success(response.result, textChunks)
          }
          is Response.Error -> ReadingState.Error
        }
      } catch (e: Exception) {
        ReadingState.Error
      }
    }
  }

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
