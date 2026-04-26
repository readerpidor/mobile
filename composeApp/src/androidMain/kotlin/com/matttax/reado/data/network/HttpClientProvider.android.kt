package com.matttax.reado.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpClient(): HttpClient = createHttpClient(OkHttp.create())
