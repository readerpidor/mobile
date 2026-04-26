package com.matttax.reado.data.model

sealed interface Response<out R, out E> {
  data class Success<out R>(val result: R) : Response<R, Nothing>
  data class Error<out E>(val error: E) : Response<Nothing, E>
}
