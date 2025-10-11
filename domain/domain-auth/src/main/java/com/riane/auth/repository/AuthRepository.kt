package com.riane.auth.repository

import android.content.Context
import com.riane.auth.model.LoginBean

interface AuthRepository {

    suspend fun loginByGoogle(dataMap: Map<String, Any>, context: Context)

    suspend fun login(username:String, password:String) : Result<LoginBean>

    suspend fun register()
}