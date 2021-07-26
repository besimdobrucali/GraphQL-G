package com.dobrucali.gorillas

import android.app.Application
import com.dobrucali.gorillas.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GorillasApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare used Android context
            androidContext(this@GorillasApplication)
            // declare modules
            modules(appModule)
        }

    }
}