package com.baimsg.contact.app

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**
 * Create by Baimsg on 2022/8/12
 *
 **/
@HiltAndroidApp
class SmallABApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }
}