package com.riane.cleanwanandroidcompose

import android.app.Application
import com.riane.utils.log.LogUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanAndroidApplication : Application(){


    override fun onCreate() {
        super.onCreate()

        LogUtils.init(this, true)
    }
}