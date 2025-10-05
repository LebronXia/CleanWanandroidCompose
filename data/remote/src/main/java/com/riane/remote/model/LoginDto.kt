package com.riane.remote.model

import com.riane.auth.model.LoginBean
import kotlinx.serialization.Serializable
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers


@Serializable
data class LoginDto (
    val admin: Boolean,
//    val chapterTops: List<Any>,
//    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)

@Mapper
interface LoginMapper{

    companion object {
        val INSTANCE: LoginMapper by lazy { Mappers.getMapper(LoginMapper::class.java) }
    }

    fun toDomain(dto: LoginDto): LoginBean
}