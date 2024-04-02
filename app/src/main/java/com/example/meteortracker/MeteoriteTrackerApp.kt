package com.example.meteortracker

import android.app.Application
import android.content.Context
import com.example.meteortracker.util.LocaleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MeteoriteTrackerApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.update(base))
    }
    override fun onCreate() {
        super.onCreate()

        val savedLocale = LocaleManager.get(this)
        LocaleManager.set(this, savedLocale)
    }
}