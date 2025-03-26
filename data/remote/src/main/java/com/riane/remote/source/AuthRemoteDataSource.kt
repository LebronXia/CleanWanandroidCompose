package com.riane.remote.source

import com.riane.remote.model.LoginDto
import com.xiamu.wanandroid.mvvm.model.entry.LoginBean
import retrofit2.http.Field

interface AuthRemoteDataSource {

    suspend fun login(username:String, password:String):  Result<LoginBean>
}