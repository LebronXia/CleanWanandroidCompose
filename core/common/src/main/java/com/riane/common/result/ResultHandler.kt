package com.riane.common.result

import com.riane.utils.log.LogUtils
import com.riane.utils.toast.ToastUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import java.io.PrintWriter
import java.io.StringWriter

object ResultHandler {

    /**
     *
     */
    fun <T> handleResult(
        scope: CoroutineScope,
        flow: Flow<Result<T>>,
        showToast: Boolean = true,
        onLoading: ()-> Unit = {},
        onSuccess: (T) -> Unit,
        onError: (String, Throwable) -> Unit = {_, _ ->},
        onFinally: () -> Unit = {}
    ){
        scope.launch {
            try {
                onLoading()
                flow.collectLatest {result ->
                    result.onSuccess {
                        onSuccess(it)
                    }.onFailure {e ->
                        handleError(e.message ?: "网络请求失败", e, showToast, onError)
                    }
                }
            }catch (e: Exception){
                handleError("请求处理异常", e, showToast, onError)
            }  finally {
                onFinally()
            }
        }
    }

    private fun handleError(
        errorMsg: String,
        throwable: Throwable,
        showToast: Boolean,
        onError: (String, Throwable) -> Unit
    ) {
        val additionalInfo = throwable?.let { getErrorTypeDescription(it) } ?: ""
        LogUtils.e(formatErrorLog(errorMsg, throwable, additionalInfo))
        onError(errorMsg, throwable)
        if (showToast) {
            ToastUtils.showError(errorMsg)
        }
    }

    /**
     * 获取错误类型描述
     */
    private fun getErrorTypeDescription(throwable: Throwable): String = when (throwable) {
        is SerializationException -> buildString {
            append("JSON解析错误\n")
            append("错误位置: ${throwable.message}")
        }

        is java.net.SocketTimeoutException -> "网络连接超时"
        is java.net.UnknownHostException -> "无法解析主机地址"
        is java.io.IOException -> "网络IO异常"
        else -> "未知异常类型"
    }

    /**
     * 获取完整的异常堆栈信息
     */
    private fun getStackTraceString(throwable: Throwable): String {
        return StringWriter().apply {
            throwable.printStackTrace(PrintWriter(this))
        }.toString()
    }

    /**
     * 格式化错误日志信息
     */
    private fun formatErrorLog(
        errorMsg: String,
        throwable: Throwable?,
        additionalInfo: String = ""
    ): String = buildString {
        appendLine("=== 网络请求错误 ===")
        appendLine("错误信息: $errorMsg")
        if (additionalInfo.isNotEmpty()) {
            appendLine("附加信息: $additionalInfo")
        }
        throwable?.let {
            appendLine("异常类型: ${it.javaClass.name}")
            appendLine("异常堆栈:")
            appendLine(getStackTraceString(it))
        }
        appendLine("==================")
    }
}