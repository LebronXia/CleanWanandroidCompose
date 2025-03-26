package com.riane.auth.repository

import com.xiamu.wanandroid.mvvm.model.entry.LoginBean
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username:String, password:String) : Result<LoginBean>

    suspend fun register()
}