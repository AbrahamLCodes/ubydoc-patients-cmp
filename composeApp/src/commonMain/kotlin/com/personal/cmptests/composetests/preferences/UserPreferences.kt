package com.personal.cmptests.composetests.preferences

import com.russhwolf.settings.Settings

interface UserPreferences {
    var username: String?
    var token: String?
    fun clear()
}

class UserPreferencesImpl(
    private val settings: Settings
) : UserPreferences {

    private companion object {
        const val KEY_USERNAME = "username"
        const val KEY_TOKEN = "auth_token"
    }

    override var username: String?
        get() = settings.getStringOrNull(KEY_USERNAME)
        set(value) {
            if (value != null) settings.putString(KEY_USERNAME, value)
            else settings.remove(KEY_USERNAME)
        }

    override var token: String?
        get() = settings.getStringOrNull(KEY_TOKEN)
        set(value) {
            if (value != null) settings.putString(KEY_TOKEN, value)
            else settings.remove(KEY_TOKEN)
        }

    override fun clear() {
        settings.clear()
    }
}