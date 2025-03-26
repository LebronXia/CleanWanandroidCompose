package com.riane.remote.api

import com.riane.network.model.BaseResponse
import com.riane.remote.model.LoginDto
import retrofit2.http.Field

interface WanAndroidNetworkApi {

    suspend fun login(@Field("username") username:String, @Field("password")password:String): BaseResponse<LoginDto>
}