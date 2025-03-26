package com.riane.remote.source

import com.riane.network.NullBodyException
import com.riane.network.model.handleApiService
import com.riane.network.safeApiCall
import com.riane.remote.api.WanAndroidNetworkApi
import com.riane.remote.model.LoginDto
import com.riane.remote.model.LoginMapper
import com.xiamu.wanandroid.mvvm.model.entry.LoginBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
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