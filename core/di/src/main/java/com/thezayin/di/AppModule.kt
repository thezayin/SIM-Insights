package com.thezayin.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.ads.GoogleManager
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.analytics.analytics.Analytics
import com.thezayin.analytics.analytics.AnalyticsImpl
import com.thezayin.data.ServerRepositoryImpl
import com.thezayin.data.network.ResultApi
import com.thezayin.data.repository.ResultRepositoryImpl
import com.thezayin.domain.repository.ResultRepository
import com.thezayin.domain.repository.ServerRepository
import com.thezayin.domain.usecase.GetResult
import com.thezayin.domain.usecase.GetResultImpl
import com.thezayin.domain.usecase.ServerList
import com.thezayin.domain.usecase.ServerListImpl
import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.home.HomeViewModel
import com.thezayin.premium.PremiumViewModel
import com.thezayin.presentation.ResultViewModel
import com.thezayin.presentation.ServerViewModel
import com.thezayin.presentation.SplashViewModel
import com.thezayin.setting.SettingViewModel
import com.thezayin.web.WebViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module











