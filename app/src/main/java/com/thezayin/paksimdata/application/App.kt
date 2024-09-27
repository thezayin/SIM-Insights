package com.thezayin.paksimdata.application

import android.app.Application
import com.thezayin.ads.di.adModule
import com.thezayin.analytics.di.analyticsModule
import com.thezayin.framework.di.featureModule
import com.thezayin.home.di.homeModule
import com.thezayin.premium.di.premiumModule
import com.thezayin.presentation.di.historyModule
import com.thezayin.presentation.di.resultModule
import com.thezayin.presentation.di.serverModule
import com.thezayin.presentation.di.splashModule
import com.thezayin.setting.di.settingModule
import com.thezayin.web.di.webModule
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
                analyticsModule,
                historyModule
            )
        }
    }
}