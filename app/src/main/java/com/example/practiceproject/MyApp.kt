package com.example.practiceproject

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.practiceproject.util.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.Module

@KoinApiExtension
class MyApp : MultiDexApplication(), KoinComponent {

    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@MyApp)
            workManagerFactory()
            modules(appModule)
        }
    }
}
