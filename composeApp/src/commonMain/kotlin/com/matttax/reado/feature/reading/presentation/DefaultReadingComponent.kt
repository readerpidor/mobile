package com.matttax.reado.feature.reading.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.matttax.reado.audio.AudioPlayer
import com.matttax.reado.audio.PlaylistItem
import com.matttax.reado.data.ReaderService
import com.matttax.reado.data.model.Response
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

  init {
    lifecycle.doOnDestroy {
      audioPlayer.release()
      scope.cancel()
    }
    load()
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
            val chunks = text.split(Regex("<<\\d+>>"))
              .map { it.trim() }
              .filter { it.isNotEmpty() }
            audioPlayer.setPlaylist(
              response.result.audioParts.map { part ->
                PlaylistItem(
                  url = part.audioUrl.replace(STORAGE_SIGNED_AUTHORITY, STORAGE_REACHABLE_AUTHORITY),
                  headers = mapOf(HttpHeaders.Host to STORAGE_SIGNED_AUTHORITY),
                )
              },
            )
            ReadingState.Success(response.result, chunks)
          }
          is Response.Error -> ReadingState.Error
        }
      } catch (e: Exception) {
        ReadingState.Error
      }
    }
  }
}
