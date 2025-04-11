package com.riane.network

data class RequestFailedException(
    val code: String,
    override val message: String? = null,
) : Exception()