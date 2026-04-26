package com.matttax.reado.data.network

import com.matttax.reado.data.service.Response
import kotlinx.serialization.Serializable

@Serializable
internal data class Envelope<R, E>(
  val success: R? = null,
  val error: E? = null,
)

internal fun <R, E> Envelope<R, E>.toResponse(): Response<R, E> = when {
  success != null -> Response.Success(success)
  error != null -> Response.Error(error)
  else -> throw IllegalStateException("Envelope has neither success nor error")
}
