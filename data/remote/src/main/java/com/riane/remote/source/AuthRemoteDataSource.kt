package com.riane.remote.source

import com.riane.auth.model.LoginBean

interface AuthRemoteDataSource {

    suspend fun login(username:String, password:String):  Result<LoginBean>
}