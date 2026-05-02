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

const val STORAGE_SIGNED_AUTHORITY = "minio:9000"
expect val STORAGE_REACHABLE_AUTHORITY: String
const val READER_AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InRlc3Qta2V5LTEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJyZWFkZXIiLCJzdWIiOiJsb2NhbC11c2VyIiwiYXVkIjpbInJlYWRlciJdLCJleHAiOjE3Nzc4MTQ5MTUsImlhdCI6MTc3NzcyODUxNX0.FgZkf0uotgA1CHt-fJGry-4AwzCwvkM446OaLy8eJ6GADC_N2x91UnCybnJB5k4QtV6hYE-XF6vcPSVeTNPV2IPrO7XfgGB9X_GhQfQex0T9l-a92AvShHM67y_tPIHidHOZAf3xV0X_CwVDLFqMsChaLe686lZ6iqgwmd7_ObXtoH5-rVvxV_DwheeZWzLQeSTYiTiVOM-YngarEscZtOnSk_AgvmYNPPFzmsT7biaoN5tv_OWHefRLXzUAmSjnZmUSoYA2eJpo9cN6auOQMukHp-yLb7ZneN-FHiox9e8lMOGR89NamepqXARRQH09i94F5xfdkEH_rv0T6wP71A"

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
