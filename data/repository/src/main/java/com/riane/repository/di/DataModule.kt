package com.riane.repository.di

import com.riane.auth.repository.AuthRepository
import com.riane.remote.api.WanAndroidNetworkApi
import com.riane.remote.source.AuthRemoteDataSource
import com.riane.remote.source.AuthRemoteDataSourceImpl
import com.riane.repository.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun providerAuthRepository(remote: AuthRemoteDataSource): AuthRepository

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(api: WanAndroidNetworkApi): AuthRemoteDataSource
}