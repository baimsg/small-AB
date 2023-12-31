package com.baimsg.contact.activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.baimsg.contact.base.BaseActivity
import com.baimsg.contact.databinding.ActivityPermissionBinding
import com.baimsg.resource.R
import com.permissionx.guolindev.PermissionX
import com.zcy.pudding.Pudding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Create by Baimsg on 2022/8/12
 *
 **/
@AndroidEntryPoint
class PermissionActivity : BaseActivity<ActivityPermissionBinding>() {
    private val permissions = listOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    override fun initView() {
        PermissionX.init(this).permissions(permissions = permissions)
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    resources.getString(R.string.permission_setting),
                    resources.getString(R.string.go_setting),
                    resources.getString(android.R.string.cancel)
                )
            }.request { allGranted, _, _ ->
                if (allGranted) {
                    nextActivity()
                } else {
                    Pudding.create(this) {
                        setChocoBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext, R.color.color_primary_variant
                            )
                        )
                        setTitleTypeface(Typeface.DEFAULT_BOLD)
                        setTitle(R.string.permission_fail)
                        onDismiss {
                            finish()
                        }
                    }.show()
                }
            }
    }

    private fun nextActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}