/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.riane.remote

import com.riane.network.NullBodyException
import com.riane.network.RequestFailedException
import com.riane.network.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//sealed interface Result<out T> {
//    data class Success<T>(val data: T) : Result<T>
//    data class Error(val exception: Throwable) : Result<Nothing>
//    data object Loading : Result<Nothing>
//}
//
//fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
//    .onStart { emit(Result.Loading) }
//    .catch { emit(Result.Error(it)) }

suspend fun <T : Any, R : Any> safeApiCall(
    call: suspend () -> BaseResponse<T>,
    transform: (T) -> R
): Result<R> {
    return try {
        val response = call()
        if (response.isSuccess()) {
            Result
            val dto = response.data ?: throw NullBodyException()
            try {
                Result.success(transform(dto))
            } catch (e: Exception) {
                Result.failure(e) // 转换错误处理
            }
        } else {
            Result.failure(RequestFailedException(response.errorCode.toString(), response.errorMsg))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun <T : Any, R : Any> safeApiCallAsFlow(
    call: suspend () -> BaseResponse<T>,
    transform: (T) -> R
): Flow<Result<R>> = flow {
    //emit(Result.Loading)
    emit(safeApiCall(call, transform))
}.catch { e ->
    emit(Result.failure(e))
}.flowOn(Dispatchers.IO)


suspend fun <T : Any> safeApiCall(
    call: suspend () -> BaseResponse<T>
): Result<T> {
    return try {
        val response = call()
        if (response.isSuccess()) {
            val dto = response.data ?: throw NullBodyException()
            try {
                Result.success(dto)
            } catch (e: Exception) {
                Result.failure(e) // 转换错误处理
            }
        } else {
            Result.failure(RequestFailedException(response.errorCode.toString(), response.errorMsg))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}

//call: BaseResponse<ListWrapper<ArticleBean>>

fun <T : Any> safeApiCallAsFlow(
    call: suspend () -> BaseResponse<T>
): Flow<Result<T>> = flow {
    //emit(Result.Loading)
    emit(safeApiCall(call))
}.catch { e ->
    emit(Result.failure(e))
}.flowOn(Dispatchers.IO)



