package com.matttax.reado.di

import com.matttax.reado.data.ReaderService
import com.matttax.reado.data.network.KtorReaderService
import com.matttax.reado.data.network.provideHttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule: Module = module {
  single { provideHttpClient() }
  single<ReaderService> { KtorReaderService(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
  appDeclaration()
  modules(appModule)
}

fun initKoin() = initKoin {}
