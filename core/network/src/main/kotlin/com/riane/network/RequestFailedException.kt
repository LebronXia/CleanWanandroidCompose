package com.riane.network

data class RequestFailedException(
    val code: Int,
    override val message: String? = null,
) : Exception()