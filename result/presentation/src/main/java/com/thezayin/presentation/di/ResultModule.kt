package com.thezayin.presentation.di

import com.thezayin.data.network.ResultApi
import com.thezayin.data.repository.ResultRepositoryImpl
import com.thezayin.domain.repository.ResultRepository
import com.thezayin.domain.usecase.GetResult
import com.thezayin.domain.usecase.GetResultImpl
import com.thezayin.presentation.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val resultModule = module {
    singleOf(::ResultApi)
    viewModelOf(::ResultViewModel)
    singleOf(::GetResultImpl) bind GetResult::class
    singleOf(::ResultRepositoryImpl) bind ResultRepository::class
}