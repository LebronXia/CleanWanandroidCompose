package com.riane.remote.api

import com.riane.network.model.BaseResponse
import com.riane.remote.model.LoginDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WanAndroidNetworkApi {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") username:String, @Field("password")password:String): BaseResponse<LoginDto>
}