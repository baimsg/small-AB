package com.chat.honey.activity

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import com.chat.base.util.extensions.logE
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

    @SuppressLint("InlinedApi")
    private val projection: Array<out String> = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    override fun initView() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                setStatusBarLightMode()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                setStatusBarDarkMode()
            }
        }

        val profileCursor = contentResolver.query(
          ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection, null, null, null
        )

        while (profileCursor?.moveToNext() == true) {
            logE(
                profileCursor.getString(0) + "\t" + profileCursor.getString(1)
            )
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