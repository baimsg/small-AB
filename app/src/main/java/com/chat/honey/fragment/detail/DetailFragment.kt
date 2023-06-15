package com.chat.honey.fragment.detail

import androidx.fragment.app.viewModels
import com.chat.honey.R
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.FragmentDetailBinding

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun initView() {
        binding.tvName.text = "${detailViewModel.id}"
    }
}