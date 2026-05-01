package com.matttax.reado.feature.reading.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.matttax.reado.data.ReaderService
import com.matttax.reado.data.model.Response
import com.matttax.reado.data.model.process.ProcessRequest
import com.matttax.reado.navigation.components.ReadingComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DefaultReadingComponent(
  componentContext: ComponentContext,
  private val readerService: ReaderService,
  private val url: String,
  private val onBack: () -> Unit,
) : ReadingComponent, ComponentContext by componentContext {

  private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

  private val _state = MutableValue<ReadingState>(ReadingState.Loading)
  override val state: Value<ReadingState> = _state

  init {
    lifecycle.doOnDestroy { scope.cancel() }
    load()
  }

  override fun onBack() {
    onBack.invoke()
  }

  private fun load() {
    scope.launch {
      _state.value = ReadingState.Loading
      _state.value = try {
        when (val response = readerService.process(ProcessRequest(url = url))) {
          is Response.Success -> {
            val text = readerService.fetchArticleText(response.result.articleUrl)
            ReadingState.Success(response.result, text)
          }
          is Response.Error -> ReadingState.Error
        }
      } catch (e: Exception) {
        ReadingState.Error
      }
    }
  }
}
