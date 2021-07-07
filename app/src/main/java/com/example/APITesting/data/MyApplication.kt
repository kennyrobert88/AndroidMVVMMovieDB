package com.example.APITesting.data.MyApplication

import android.app.Application
import com.example.APITesting.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    dataSourceModule,
                    dataSourceFactoryModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}