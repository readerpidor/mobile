package com.matttax.reado

import android.app.Application
import com.matttax.reado.di.initKoin

class ReadoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    initKoin()
  }
}
