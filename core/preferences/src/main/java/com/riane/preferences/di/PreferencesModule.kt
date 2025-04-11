package com.riane.preferences.di

import android.content.Context
import com.riane.preferences.manager.DataStoreManager
import com.riane.preferences.manager.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesManager(context: Context): PreferencesManager{
        return  DataStoreManager(context)
    }
}