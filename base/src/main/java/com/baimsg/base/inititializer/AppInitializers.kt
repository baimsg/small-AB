package com.baimsg.base.inititializer

import android.app.Application

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
class AppInitializers(private vararg val initializers: AppInitializer) : AppInitializer {
    override fun init(application: Application) {
        initializers.forEach {
            it.init(application)
        }
    }
}

interface AppInitializer {
    fun init(application: Application)
}