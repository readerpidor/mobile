package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

const val READER_BASE_URL = "http://localhost:8080"
const val READER_AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InRlc3Qta2V5LTEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJyZWFkZXIiLCJzdWIiOiJsb2NhbC11c2VyIiwiYXVkIjpbInJlYWRlciJdLCJleHAiOjE3NzcyNDUwMDQsImlhdCI6MTc3NzE1ODYwNH0.IqKw8zChugGq-C8XqzR9pYiGRwqTWCb4DEjiDYRxudWB2gngEbSW7s7NjKJD5Rhy_Qmae_vxInKqRVx9ipjs1AJp7a-YvfeWVd0Bosy06lM0NhCkksKwcW0rqm1ldPocdRnxYko9d4WJFPDd7pRbI9vQdEGdfVApkBBARXqcE4UY-LdrK8VdWL_P0bgBtv3iIhdyOJGB6_s1jZA9ND7zPB8zxxexqc45Xv4IYSu632rXoa0S0x5x7r3O0hJ2RXt7nXXtOEi7SOCUWprkfD0jrimeu_stRQddghb9Do1seDkh24zvYIWH2beM2zWGqbr2asMqaXSlbL0ZGtR3Ktg3Mw"

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
    header(HttpHeaders.Authorization, READER_AUTH_TOKEN)
  }
}
