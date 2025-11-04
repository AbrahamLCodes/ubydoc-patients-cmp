package com.personal.cmptests.composetests.di

import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.repository.AuthRepository
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.repository.AuthRepositoryImpl
import com.personal.cmptests.composetests.features.maintabs.ProfileTabScreen.viewmodel.AuthViewModel
import com.personal.cmptests.composetests.network.apiservices.ApiService
import com.personal.cmptests.composetests.network.createHttpClient
import com.personal.cmptests.composetests.network.dto.LocalDataSource

import org.koin.dsl.module

val appModule = module {
    // Network
    single { createHttpClient() }
    single { ApiService(get()) }

    // Data
    single { LocalDataSource() }

    // Repository
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    // ViewModels
    single { AuthViewModel(get()) }
}