package com.matttax.reado

import android.app.Application
import com.matttax.reado.di.initKoin
import org.koin.android.ext.koin.androidContext

class ReadoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    initKoin {
      androidContext(this@ReadoApplication)
    }
  }
}
