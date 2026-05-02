package com.matttax.reado.di

import com.matttax.reado.audio.AudioPlayer
import com.matttax.reado.audio.IosAudioPlayer
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
  single<AudioPlayer> { IosAudioPlayer() }
}
