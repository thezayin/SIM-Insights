package com.thezayin.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.thezayin.presentation.SplashViewModel
import org.koin.dsl.module

val splashModule = module {
    viewModelOf(::SplashViewModel)
}