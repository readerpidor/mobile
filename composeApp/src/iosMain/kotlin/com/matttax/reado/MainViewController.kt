package com.matttax.reado

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.matttax.reado.navigation.DefaultRootComponent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
  val lifecycle = LifecycleRegistry()
  val root = DefaultRootComponent(
    componentContext = DefaultComponentContext(lifecycle = lifecycle),
  )
  lifecycle.resume()
  return ComposeUIViewController { App(root) }
}
