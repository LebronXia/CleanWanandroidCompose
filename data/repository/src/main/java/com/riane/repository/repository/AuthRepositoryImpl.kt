package com.riane.repository.repository

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.riane.auth.repository.AuthRepository
import com.riane.remote.source.AuthRemoteDataSource
import com.riane.auth.model.LoginBean
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

//整合多数据源并定义数据优先级策略
class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun loginByGoogle(dataMap: Map<String, Any>, context: Context){
        //GoogleSignInOptions()
        // https://juejin.cn/post/7183633464069980216
        //https://developer.android.com/identity/sign-in/credential-manager-siwg?hl=zh-cn
        val WEB_CLIENT_ID =
            "154550599425-vqrcqt4d6bhldg7dkc8un8rai2eg9cd8.apps.googleusercontent.com"
        //val context = getAct;
        val credentialManager = CredentialManager.create(context)
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("sha-256")
        val digest = md.digest(bytes)
        val hashCodeNonce = digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(WEB_CLIENT_ID)
            .setNonce(hashCodeNonce)
            .build()

        val request: GetCredentialRequest =
            GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()

        val result = credentialManager.getCredential(request = request, context = context)

        val credential = result.credential
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        val googleId = googleIdTokenCredential.id
        val googleToken = googleIdTokenCredential.idToken

        val localMap = dataMap.toMutableMap()
        localMap["thirdId"] = googleId
        localMap["token"] = googleToken
        localMap["type"] = "google"

        //请求
    }

    override suspend fun login(username: String, password: String): Result<LoginBean> {
        return authRemoteDataSource.login(username, password)
    }

    override suspend fun register() {

    }


}