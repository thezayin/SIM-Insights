package com.thezayin.web.di

import com.thezayin.web.WebViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val webModule = module {
    viewModelOf(::WebViewModel)
}
