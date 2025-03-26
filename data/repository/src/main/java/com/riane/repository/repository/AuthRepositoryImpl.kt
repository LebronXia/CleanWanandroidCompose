package com.riane.repository.repository

import com.riane.auth.repository.AuthRepository
import com.riane.common.network.Dispatcher
import com.riane.common.network.NiaDispatchers
import com.riane.remote.api.WanAndroidNetworkApi
import com.riane.remote.source.AuthRemoteDataSource
import com.xiamu.wanandroid.mvvm.model.entry.LoginBean
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//整合多数据源并定义数据优先级策略
class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository{
    override suspend fun login(username:String, password:String) : Result<LoginBean> {
        return authRemoteDataSource.login(username, password)
    }

    override suspend fun register() {

    }


}