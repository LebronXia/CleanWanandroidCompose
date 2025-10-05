package com.riane.repository.repository

import com.riane.auth.repository.AuthRepository
import com.riane.remote.source.AuthRemoteDataSource
import com.riane.auth.model.LoginBean
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