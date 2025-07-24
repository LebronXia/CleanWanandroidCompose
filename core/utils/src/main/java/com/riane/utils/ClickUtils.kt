package com.riane.utils

import android.os.SystemClock

object ClickUtils {

    private var lastClickTimestamp = arrayOf(0L, 0L, 0L)
    fun isFastClick(thresholdValue: Int = 300, index: Int = 0): Boolean {
        val currentClickTimestamp = SystemClock.elapsedRealtime()
        if (currentClickTimestamp - lastClickTimestamp[index] < thresholdValue) {
            return true
        }
        lastClickTimestamp[index] = currentClickTimestamp
        return false
    }
}