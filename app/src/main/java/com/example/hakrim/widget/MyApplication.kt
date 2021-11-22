package com.example.hakrim.widget

import android.app.Application
import com.example.hakrim.di.myModule
import com.example.hakrim.di.retrofitModule
import com.example.hakrim.di.viewModelPart
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin이 로그를 남기는 레벨을 지정 - Level.INFO by default
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(myModule)

        }
    }
}