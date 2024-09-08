package com.example.catfact

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatFactApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}