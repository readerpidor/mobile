package com.matttax.reado

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.matttax.reado.navigation.DefaultRootComponent
import org.koin.mp.KoinPlatform.getKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
  val lifecycle = LifecycleRegistry()
  val root = DefaultRootComponent(
    componentContext = DefaultComponentContext(lifecycle = lifecycle),
    readerService = getKoin().get(),
    audioPlayer = getKoin().get(),
  )
  lifecycle.resume()
  return ComposeUIViewController { App(root) }
}
