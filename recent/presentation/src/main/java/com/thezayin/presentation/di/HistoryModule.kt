package com.thezayin.presentation.di

import com.thezayin.data.di.provideDatabase
import com.thezayin.data.di.provideHistoryDao
import com.thezayin.data.repository.HistoryRepositoryImpl
import com.thezayin.domain.repository.HistoryRepository
import com.thezayin.domain.usecase.AddHistory
import com.thezayin.domain.usecase.AddHistoryImpl
import com.thezayin.domain.usecase.ClearHistoryUseCase
import com.thezayin.domain.usecase.ClearHistoryUseCaseImpl
import com.thezayin.presentation.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val historyModule = module {
    single { provideDatabase(get()) }
    single { provideHistoryDao(get()) }
    singleOf(::HistoryRepositoryImpl) bind HistoryRepository::class
    singleOf(::AddHistoryImpl) bind AddHistory::class
    viewModelOf(::HistoryViewModel)
    singleOf(::ClearHistoryUseCaseImpl) bind ClearHistoryUseCase::class
}