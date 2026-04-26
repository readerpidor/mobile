package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

const val READER_BASE_URL = "http://localhost:8080"

@OptIn(ExperimentalSerializationApi::class)
val ReaderJson: Json = Json {
  ignoreUnknownKeys = true
  isLenient = true
  encodeDefaults = true
  explicitNulls = false
  namingStrategy = JsonNamingStrategy.SnakeCase
}

fun createHttpClient(engine: HttpClientEngine): HttpClient = HttpClient(engine) {
  install(ContentNegotiation) {
    json(ReaderJson)
  }
  install(Logging) {
    level = LogLevel.HEADERS
  }
  defaultRequest {
    url(READER_BASE_URL)
    contentType(ContentType.Application.Json)
  }
}
