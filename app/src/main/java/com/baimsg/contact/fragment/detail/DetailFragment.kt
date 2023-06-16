package com.baimsg.contact.fragment.detail

import androidx.fragment.app.viewModels
import com.baimsg.contact.R
import com.baimsg.contact.base.BaseFragment
import com.baimsg.contact.databinding.FragmentDetailBinding
import com.baimsg.contact.util.extensions.repeatOnLifecycleStarted
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
        binding.tvName.text = "${detailViewModel.id}"
    }

    override fun initLiveData() {
        repeatOnLifecycleStarted {
            detailViewModel.observeMessage.collectLatest {
                it ?: return@collectLatest

            }
        }
    }


}