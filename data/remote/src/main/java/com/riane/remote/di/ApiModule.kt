package com.riane.remote.di

import com.riane.remote.api.HomeService
import com.riane.remote.api.WanAndroidNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideWanAndroidApi(retrofit: Retrofit): WanAndroidNetworkApi =
        retrofit.create(WanAndroidNetworkApi::class.java)

    @Provides
    @Singleton
    fun providerHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)
}