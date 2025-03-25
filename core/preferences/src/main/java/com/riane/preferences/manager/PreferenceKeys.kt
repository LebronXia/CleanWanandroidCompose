package com.riane.preferences.manager

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode_enabled")
    val USER_TOKEN = stringPreferencesKey("user_auth_token")
}