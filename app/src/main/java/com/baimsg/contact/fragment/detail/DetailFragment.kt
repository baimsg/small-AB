package com.baimsg.contact.fragment.detail

import android.util.Base64
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baimsg.contact.R
import com.baimsg.contact.base.BaseFragment
import com.baimsg.contact.databinding.FragmentDetailBinding
import com.baimsg.contact.extensions.loadImage
import com.baimsg.contact.util.extensions.repeatOnLifecycleStarted
import com.baimsg.contact.util.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun initView() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.ivEdit.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToEditFragment(
                    id = detailViewModel.id ?: 0
                )
            )
        }


        binding.tvDeleteContacts.setOnClickListener {
            detailViewModel.delete()
        }
    }

    override fun initLiveData() {
        repeatOnLifecycleStarted {
            detailViewModel.observeMessage.collectLatest {
                it ?: return@collectLatest
                if (it.contains("成功")) findNavController().navigateUp()

                show(text = it, onDismiss = detailViewModel::clearMessage)
            }
        }

        repeatOnLifecycleStarted {
            detailViewModel.observeContacts.collectLatest {
                with(binding) {
                    tvDeleteContacts.show(it != null)
                    it ?: return@collectLatest
                    ivHeader.loadImage(
                        Base64.decode(it.header ?: "", Base64.URL_SAFE), round = 120f
                    )
                    tvName.text = it.name
                    tvNumber.text = it.number
                    tvEmail.text = it.emailText
                    tvAddress.text = it.addressText
                    tvDescribe.text = it.describeText
                }
            }
        }
    }


}