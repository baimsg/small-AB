package com.baimsg.contact.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.baimsg.contact.util.extensions.setStatusBarLightMode
import com.baimsg.contact.util.extensions.translucent
import java.lang.reflect.ParameterizedType

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected val binding: VB by lazy {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<*>
        val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //沉浸式状态栏
        translucent()
        setStatusBarLightMode()
        setContentView(binding.root)
        initView()
    }

    abstract fun initView()
}