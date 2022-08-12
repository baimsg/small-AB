package com.chat.honey.app

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**
 * Create by Baimsg on 2022/8/12
 *
 **/
@HiltAndroidApp
class VideoHoneyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}