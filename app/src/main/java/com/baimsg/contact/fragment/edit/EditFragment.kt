package com.baimsg.contact.fragment.edit

import android.util.Base64
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baimsg.base.util.extensions.logE
import com.baimsg.contact.R
import com.baimsg.contact.base.BaseFragment
import com.baimsg.contact.databinding.FragmentEditBinding
import com.baimsg.contact.extensions.loadImage
import com.baimsg.contact.util.extensions.hideKeyboard
import com.baimsg.contact.util.extensions.repeatOnLifecycleStarted
import com.baimsg.contact.util.extensions.show
import com.baimsg.contact.util.extensions.showKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy


/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@AndroidEntryPoint
class EditFragment : BaseFragment<FragmentEditBinding>(R.layout.fragment_edit) {

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                requireContext().contentResolver.openInputStream(uri).use { inputStream ->
                    editViewModel.updateHeader(inputStream?.readBytes())
                }
            }
        }

    private val editViewModel by viewModels<EditViewModel>()

    override fun initView() {
        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvSave.setOnClickListener {
            editViewModel.save()
        }

        binding.ivHeader.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ImageOnly))
        }

        binding.editName.apply {
            addTextChangedListener {
                binding.ivClearName.show(!it.isNullOrEmpty())
                editViewModel.updateName(it.toString())
            }
            showKeyboard(true)
        }
        binding.ivClearName.setOnClickListener {
            binding.editName.text = null
        }

        binding.editNumber.apply {
            addTextChangedListener {
                binding.ivClearNumber.show(!it.isNullOrEmpty())
                editViewModel.updateNumber(it.toString())
            }
        }
        binding.ivClearNumber.setOnClickListener {
            binding.editNumber.text = null
        }

        binding.editEmail.apply {
            addTextChangedListener {
                binding.ivClearEmail.show(!it.isNullOrEmpty())
                editViewModel.updateEmail(it.toString())
            }
        }
        binding.ivClearEmail.setOnClickListener {
            binding.editEmail.text = null
        }

        binding.editAddress.apply {
            addTextChangedListener {
                binding.ivClearAddress.show(!it.isNullOrEmpty())
                editViewModel.updateAddress(it.toString())
            }
        }
        binding.ivClearAddress.setOnClickListener {
            binding.editAddress.text = null
        }

        binding.editDescribe.apply {
            addTextChangedListener {
                binding.ivClearDescribe.show(!it.isNullOrEmpty())
                editViewModel.updateDescribe(it.toString())
            }
        }
        binding.ivClearDescribe.setOnClickListener {
            binding.editDescribe.text = null
        }
    }


    override fun initLiveData() {
        repeatOnLifecycleStarted {
            editViewModel.observeMessage.collectLatest {
                it ?: return@collectLatest
                show(it, onDismiss = editViewModel::clearMessage)
                if (it.contains("成功")) {
                    findNavController().navigateUp()
                }
            }
        }

        repeatOnLifecycleStarted {
            editViewModel.observeContact.distinctUntilChangedBy {
                it.header
            }.collectLatest {
                binding.ivHeader.loadImage(Base64.decode(it.header ?: "", Base64.URL_SAFE), 120f)
            }
        }

        repeatOnLifecycleStarted {
            editViewModel.observeContact.collectLatest {
                logE(it.copy(header = it.header?.substring(0, 5)))
                with(binding) {
                    with(editName) {
                        if (text.isNullOrEmpty()) {
                            setText(it.name)
                            setSelection(it.name.length)
                        }
                    }
                    with(editNumber) {
                        if (text.isNullOrEmpty()) setText(it.number)
                    }
                    with(editEmail) {
                        if (text.isNullOrEmpty()) setText(it.email)
                    }
                    with(editAddress) {
                        if (text.isNullOrEmpty()) setText(it.address)
                    }
                    with(editDescribe) {
                        if (text.isNullOrEmpty()) setText(it.describe)
                    }
                }
            }
        }
    }

    override fun onPause() {
        binding.editName.hideKeyboard()
        super.onPause()
    }
}