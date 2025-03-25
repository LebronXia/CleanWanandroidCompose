package com.riane.preferences.manager

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    // 读取数据
    suspend fun <T> get(key: Preferences.Key<T>, defaultValue: T): T
    fun <T> getAsFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    // 写入数据
    suspend fun <T> put(key: Preferences.Key<T>, value: T)

    // 删除数据
    suspend fun <T> remove(key: Preferences.Key<T>)

    // 清空数据
    suspend fun clear()
}