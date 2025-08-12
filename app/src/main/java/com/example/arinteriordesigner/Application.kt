package com.example.arinteriordesigner

import android.app.Application
import com.example.arinteriordesigner.data.local.prefs.PrefManager
import com.facebook.FacebookSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        PrefManager.init(this)
    }
}