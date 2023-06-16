package com.baimsg.contact.base

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.baimsg.resource.R
import com.zcy.pudding.Pudding
import java.lang.reflect.ParameterizedType

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
abstract class BaseFragment<VB : ViewBinding>(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private var _binding: VB? = null

    private var isInitData: Boolean = false

    protected val binding by lazy {
        _binding!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
        )
        _binding = method.invoke(null, layoutInflater, container, false) as VB

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        if (!isInitData) {
            initData()
            isInitData = true
        }
        initLiveData()
    }

    abstract fun initView()

    /**
     * 只需要请求一次的数据
     */
    open fun initData() = Unit

    /**
     * 加载livedata数据
     */
    open fun initLiveData() = Unit


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    open fun show(text: String? = null, @StringRes textRes: Int? = null, onDismiss: () -> Unit) {
        Pudding.create(requireActivity() as AppCompatActivity) {
            setChocoBackgroundColor(
                ContextCompat.getColor(
                    requireContext(), R.color.color_primary_variant
                )
            )
            setTitleTypeface(Typeface.DEFAULT_BOLD)
            text?.let {
                setTitle(it)
            }
            textRes?.let {
                setTitle(it)
            }
            onDismiss(onDismiss)
        }.show()
    }


}