package com.thezayin.paksimdata.presentation.application

import android.app.Application
import com.thezayin.ads.di.adModule
import com.thezayin.analytics.di.analyticsHelperModule
import com.thezayin.paksimdata.di.appModule
import com.thezayin.paksimdata.presentation.servers.di.serverModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
            modules(serverModule)
            modules(adModule)
            modules(analyticsHelperModule)
        }
    }
}