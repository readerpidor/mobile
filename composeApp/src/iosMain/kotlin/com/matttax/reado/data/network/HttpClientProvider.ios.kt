package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual val READER_BASE_URL: String = "http://localhost:8080"

actual val STORAGE_REACHABLE_AUTHORITY: String = "localhost:9000"

actual fun provideHttpClient(): HttpClient = createHttpClient(Darwin.create())
