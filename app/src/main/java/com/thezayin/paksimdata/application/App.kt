package com.thezayin.paksimdata.application

import android.app.Application
import com.thezayin.di.adModule
import com.thezayin.di.analyticsModule
import com.thezayin.di.featureModule
import com.thezayin.di.homeModule
import com.thezayin.di.premiumModule
import com.thezayin.di.resultModule
import com.thezayin.di.serverModule
import com.thezayin.di.settingModule
import com.thezayin.di.splashModule
import com.thezayin.di.webModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                adModule,
                webModule,
                featureModule,
                homeModule,
                splashModule,
                resultModule,
                serverModule,
                settingModule,
                premiumModule,
                analyticsModule
            )
        }
    }
}