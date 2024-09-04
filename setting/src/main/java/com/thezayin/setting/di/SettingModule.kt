package com.thezayin.setting.di

import com.thezayin.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val settingModule = module {
    viewModelOf(::SettingViewModel)
}