package com.matttax.reado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.matttax.reado.navigation.DefaultRootComponent
import org.koin.mp.KoinPlatform.getKoin

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge()
    super.onCreate(savedInstanceState)

    val root = DefaultRootComponent(
      componentContext = defaultComponentContext(),
      readerService = getKoin().get(),
    )

    setContent {
      App(root)
    }
  }
}
