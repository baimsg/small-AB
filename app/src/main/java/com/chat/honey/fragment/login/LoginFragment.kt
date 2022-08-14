package com.chat.honey.fragment.login

import android.graphics.Typeface
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.chat.honey.R
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.FragmentLoginBinding
import com.chat.honey.util.extensions.show
import com.chat.honey.util.extensions.showKeyboard
import com.zcy.pudding.Pudding
import kotlin.random.Random

/**
 * Create by Baimsg on 2022/8/14
 *
 **/
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loading by lazy {
        MaterialDialog(requireContext())
            .maxWidth(R.dimen.loading)
            .cancelOnTouchOutside(false)
            .cancelable(false)
            .customView(R.layout.dialog_loading)
    }

    override fun initView() {
        binding.editAccount.apply {
            showKeyboard(true)
            addTextChangedListener {
                binding.ivAccountClear.show(it.toString().isNotBlank())
            }
        }

        binding.ivAccountClear.setOnClickListener {
            binding.editAccount.text = null
        }

        binding.editPassword.apply {
            addTextChangedListener {
                binding.ivPasswordClear.show(it.toString().isNotBlank())
            }
        }

        binding.ivPasswordShow.apply {
            setOnClickListener {
                isSelected = !isSelected
                binding.editPassword.let {
                    it.transformationMethod =
                        if (isSelected) HideReturnsTransformationMethod.getInstance()
                        else PasswordTransformationMethod.getInstance()
                    it.setSelection(it.text?.length ?: 0)
                }
            }
        }

        binding.ivPasswordClear.setOnClickListener {
            binding.editPassword.text = null
        }

        binding.ivLogin.setOnClickListener {
            if (binding.editAccount.text.isNullOrBlank()) {
                show("账号不能为空")
                binding.editAccount.showKeyboard(true)
                return@setOnClickListener
            }
            if (binding.editPassword.text.isNullOrBlank()) {
                show("密码不能为空")
                binding.editPassword.showKeyboard(true)
                return@setOnClickListener
            }
            loading.show()
            it.postDelayed({
                loading.dismiss()
                show(text = "登录失败):")
            }, (Random.nextLong(1) + 1) * 1000)
        }

        binding.tvRegister.setOnClickListener {
            show(textRes = R.string.register_tip);
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    private fun show(text: String? = null, @StringRes textRes: Int? = null) {
        Pudding.create(requireActivity() as AppCompatActivity) {
            setChocoBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_primary_variant
                )
            )
            setTitleTypeface(Typeface.DEFAULT_BOLD)
            text?.let {
                setTitle(it)
            }
            textRes?.let {
                setTitle(it)
            }
        }.show()
    }
}