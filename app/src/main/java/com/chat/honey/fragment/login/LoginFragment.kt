package com.chat.honey.fragment.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import com.chat.honey.R
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.FragmentLoginBinding
import com.chat.honey.util.extensions.show
import com.chat.honey.util.extensions.showKeyboard

/**
 * Create by Baimsg on 2022/8/14
 *
 **/
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun initView() {

        binding.editAccount.apply {
            showKeyboard(true)
            addTextChangedListener {
                binding.ivAccountClear.show(it.toString().isNotBlank())
            }
        }

        binding.editPassword.apply {
            addTextChangedListener {
                binding.ivPasswordClear.show(it.toString().isNotBlank())
            }
        }
        binding.ivLogin.setOnClickListener {

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

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}