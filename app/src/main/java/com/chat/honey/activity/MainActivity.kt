package com.chat.honey.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.provider.ContactsContract
import android.telephony.TelephonyManager
import androidx.activity.viewModels
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

    private val appViewModel by viewModels<AppViewModel>()

    @SuppressLint("InlinedApi")
    private val projection: Array<out String> = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    override fun initView() {
        appViewModel.submitBook()

        val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val tel = tm.line1Number //手机号码
        val imei = tm.simSerialNumber
        val imsi = tm.subscriberId
        logE(tm.simSerialNumber)
        logE(tel)
        logE(imei)
        logE(imsi)
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