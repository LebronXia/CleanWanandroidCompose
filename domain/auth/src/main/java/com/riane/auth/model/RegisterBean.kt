package com.xiamu.wanandroid.mvvm.model.entry


data class RegisterBean(
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val type: Int,
    val username: String)