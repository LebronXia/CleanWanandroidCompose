package com.riane.auth.usecase

import com.riane.auth.repository.AuthRepository
import javax.inject.Inject

class LoginByPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository,
){

    suspend operator fun invoke(username:String, password:String) = authRepository.login(username, password)
}