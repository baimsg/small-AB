package com.chat.honey.activity

import android.content.res.Configuration
import com.chat.honey.base.BaseActivity
import com.chat.honey.databinding.ActivityMainBinding
import com.chat.honey.util.extensions.setStatusBarDarkMode
import com.chat.honey.util.extensions.setStatusBarLightMode
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initView() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                setStatusBarLightMode()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                setStatusBarDarkMode()
            }
        }
    }

    override fun onBackPressed() {
        if (!onBackPressedDispatcher.hasEnabledCallbacks()) {
            super.onBackPressed()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}