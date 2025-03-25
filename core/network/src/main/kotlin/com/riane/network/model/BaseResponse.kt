package com.riane.network.model

import kotlinx.serialization.Serializable

@Serializable
private data class BaseResponse<T> (
    val data : T?,
    val errorCode: Int,
    val errorMsg: String?
){
    fun isSuccess(): Boolean = errorCode == 0
}