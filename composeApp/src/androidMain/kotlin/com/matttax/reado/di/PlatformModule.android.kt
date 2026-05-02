package com.matttax.reado.di

import com.matttax.reado.audio.AndroidAudioPlayer
import com.matttax.reado.audio.AudioPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
  single<AudioPlayer> { AndroidAudioPlayer(androidContext()) }
}
