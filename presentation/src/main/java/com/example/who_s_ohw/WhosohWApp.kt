package com.example.who_s_ohw

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.who_s_ohw.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WhosohWApp : Application(){


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WhosohWApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))

        }

    }
}