package com.riane.preferences.manager

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
):PreferencesManager{

    private val dataStore by lazy{
        PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile("app_preferences") })
    }

    override suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T {
        return dataStore.data.first()[key] ?: defaultValue
    }

    override fun <T> getAsFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { it[key] ?: defaultValue }
    }

    override suspend fun <T> put(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences -> preferences[key] = value }
    }

    override suspend fun <T> remove(key: Preferences.Key<T>) {
        dataStore.edit { it.remove(key) }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}