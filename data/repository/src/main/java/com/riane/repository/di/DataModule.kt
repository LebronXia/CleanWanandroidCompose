package com.riane.repository.di

import com.riane.auth.repository.AuthRepository
import com.riane.domian_home.repository.HomeRepository
import com.riane.remote.api.WanAndroidNetworkApi
import com.riane.remote.source.AuthRemoteDataSource
import com.riane.remote.source.AuthRemoteDataSourceImpl
import com.riane.repository.repository.AuthRepositoryImpl
import com.riane.repository.repository.HomeRepositoryImpl
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
    abstract fun providerAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun providerHomeRepository(authRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun provideRemoteDataSource(dataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}