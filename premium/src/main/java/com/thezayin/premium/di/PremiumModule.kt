package com.thezayin.premium.di

import com.thezayin.premium.PremiumViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val premiumModule = module {
    viewModelOf(::PremiumViewModel)
}