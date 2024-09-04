package com.thezayin.presentation.di

import com.thezayin.data.ServerRepositoryImpl
import com.thezayin.domain.repository.ServerRepository
import com.thezayin.domain.usecase.ServerList
import com.thezayin.domain.usecase.ServerListImpl
import com.thezayin.presentation.ServerViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val serverModule = module {
    viewModelOf(::ServerViewModel)
    singleOf(::ServerListImpl) bind ServerList::class
    singleOf(::ServerRepositoryImpl) bind ServerRepository::class
}
