package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect val READER_BASE_URL: String
expect val STORAGE_REACHABLE_AUTHORITY: String

const val STORAGE_SIGNED_AUTHORITY = "minio:9000"

val ReaderJson: Json = Json {
  ignoreUnknownKeys = true
  isLenient = true
  encodeDefaults = true
  explicitNulls = false
}

fun createHttpClient(engine: HttpClientEngine): HttpClient = HttpClient(engine) {
  install(ContentNegotiation) {
    json(ReaderJson)
  }
  install(Logging) {
    logger = Logger.SIMPLE
    level = LogLevel.ALL
  }
  install(HttpTimeout) {
    requestTimeoutMillis = 5 * 60 * 1000
    connectTimeoutMillis = 5 * 60 * 1000
    socketTimeoutMillis = 5 * 60 * 1000
  }
  defaultRequest {
    url(READER_BASE_URL)
    contentType(ContentType.Application.Json)
    header(HttpHeaders.Authorization, READER_AUTH_TOKEN)
  }
}
