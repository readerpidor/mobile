package com.matttax.reado.di

import com.matttax.reado.feature.reading.domain.AudioPlayer
import com.matttax.reado.feature.reading.domain.IosAudioPlayer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
  single<AudioPlayer> { IosAudioPlayer() }
}
