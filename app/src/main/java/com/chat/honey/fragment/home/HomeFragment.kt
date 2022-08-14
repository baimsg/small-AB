package com.chat.honey.fragment.home

import androidx.navigation.fragment.findNavController
import com.chat.honey.R
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.FragmentHomeBinding

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initView() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }
}