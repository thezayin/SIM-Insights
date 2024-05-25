package com.thezayin.paksimdata.di

import com.thezayin.paksimdata.data.remote.network.ApiService
import com.thezayin.paksimdata.data.remote.repository.SimRepositoryImpl
import com.thezayin.paksimdata.domain.repository.SimRepository
import com.thezayin.paksimdata.domain.usecase.RemoteUseCase
import com.thezayin.paksimdata.domain.usecase.RemoteUseCaseImpl
import com.thezayin.paksimdata.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import com.thezayin.framework.remote.RemoteConfig
import org.koin.dsl.bind
import com.thezayin.paksimdata.presentation.activities.MainViewModel
import org.koin.dsl.module

val appModule = module {
    singleOf(::ApiService)
    singleOf(::RemoteConfig)

    factoryOf(::SimRepositoryImpl) bind SimRepository::class
    factoryOf(::RemoteUseCaseImpl) bind RemoteUseCase::class
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainViewModel)
}