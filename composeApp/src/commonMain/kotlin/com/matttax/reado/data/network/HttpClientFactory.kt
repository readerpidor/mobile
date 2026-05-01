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
const val READER_AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InRlc3Qta2V5LTEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJyZWFkZXIiLCJzdWIiOiJsb2NhbC11c2VyIiwiYXVkIjpbInJlYWRlciJdLCJleHAiOjE3Nzc3MjY2MDUsImlhdCI6MTc3NzY0MDIwNX0.TA1JMZDBynxp7Xqg3_ajvPG3iqdTZJVYCr4UekwKBPMmxM9GnYEhty5DbS7j7k6TqtTzHQ78flxrxBOtGxlW3R4Q_grrZAnMXmHnVelu8ma6CQBWsXJSMsX9Vtyf3S61VgifmwGVjNeBjfBSoQhOHkKt0rYxeHAvwWC2HXJ_V5DzJB74-4-xzNTDaLwRgM1WYdvnweAMIBWTlG1770eVucTWZgqkqwJ5h5DnJF8e1maAfIxtjTEm5PYtsG7g7PpaRrbTujawqu-rUgrN6l_BQiRXiluR1bzcS0RxfhDmE_PNhPNrnoHDHSpP5jqDdtfzehKUXIM1-OtvpfPJUXMQgQ"

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
