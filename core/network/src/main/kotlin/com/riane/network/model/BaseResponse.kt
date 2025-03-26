package com.riane.network.model

import com.riane.network.RequestFailedException
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    val data : T,
    val errorCode: Int,
    val errorMsg: String?
){
    fun isSuccess(): Boolean = errorCode == 0
}

fun <T> BaseResponse<T>.handleApiService(): T {
    return if (isSuccess()){
        Result.success(data)
    } else {
        Result.failure(RequestFailedException(errorCode.toString(), errorMsg))
    }.getOrThrow()
}