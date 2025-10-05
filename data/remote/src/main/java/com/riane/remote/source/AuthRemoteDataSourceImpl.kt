package com.riane.remote.source

import com.riane.remote.safeApiCall
import com.riane.remote.api.WanAndroidNetworkApi
import com.riane.remote.model.LoginMapper
import com.riane.auth.model.LoginBean
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: WanAndroidNetworkApi
)  : AuthRemoteDataSource{

    override suspend fun login(username:String, password:String) : Result<LoginBean>{
        return safeApiCall(call = {
            api.login(username, password)
        }, transform = { dto ->
            LoginMapper.INSTANCE.toDomain(dto)
        })
    }
}