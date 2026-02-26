package com.example.sublandlord

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SubLandlordApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
