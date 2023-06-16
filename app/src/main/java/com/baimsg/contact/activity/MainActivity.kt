package com.baimsg.contact.activity

import android.content.res.Configuration
import com.baimsg.contact.base.BaseActivity
import com.baimsg.contact.databinding.ActivityMainBinding
import com.baimsg.contact.util.extensions.setStatusBarDarkMode
import com.baimsg.contact.util.extensions.setStatusBarLightMode
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

}