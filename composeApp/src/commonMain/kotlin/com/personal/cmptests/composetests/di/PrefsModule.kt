package com.personal.cmptests.composetests.di

import com.personal.cmptests.composetests.preferences.UserPreferences
import com.personal.cmptests.composetests.preferences.UserPreferencesImpl
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val prefsModule = module {
    single<Settings> { Settings() }
    single<UserPreferences> { UserPreferencesImpl(get()) }
}