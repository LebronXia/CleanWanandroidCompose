package com.riane.auth.repository

import com.riane.auth.model.LoginBean

interface AuthRepository {
    suspend fun login(username:String, password:String) : Result<LoginBean>

    suspend fun register()
}