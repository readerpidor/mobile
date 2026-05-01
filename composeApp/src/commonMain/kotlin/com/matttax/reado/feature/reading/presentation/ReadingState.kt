package com.matttax.reado.feature.reading.presentation

import com.matttax.reado.data.model.process.ProcessResult

sealed interface ReadingState {
  data object Loading : ReadingState
  data object Error : ReadingState
  data class Success(val result: ProcessResult, val text: String) : ReadingState
}
