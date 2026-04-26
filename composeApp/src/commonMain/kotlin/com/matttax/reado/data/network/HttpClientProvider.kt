package com.matttax.reado.data.network

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient
