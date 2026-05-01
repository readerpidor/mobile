package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual val READER_BASE_URL: String = "http://10.0.2.2:8080"

actual val STORAGE_REACHABLE_AUTHORITY: String = "10.0.2.2:9000"

actual fun provideHttpClient(): HttpClient = createHttpClient(OkHttp.create())
