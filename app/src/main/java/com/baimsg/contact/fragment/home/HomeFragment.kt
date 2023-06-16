package com.baimsg.contact.fragment.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.baimsg.contact.R
import com.baimsg.contact.adapter.ContactAdapter
import com.baimsg.contact.base.BaseFragment
import com.baimsg.contact.databinding.EmptyBaseBinding
import com.baimsg.contact.databinding.FooterContactsBinding
import com.baimsg.contact.databinding.FragmentHomeBinding
import com.baimsg.contact.util.extensions.hideKeyboard
import com.baimsg.contact.util.extensions.repeatOnLifecycleStarted
import com.baimsg.contact.util.extensions.show
import com.baimsg.data.entities.Contacts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val loading by lazy {
        MaterialDialog(requireContext()).maxWidth(com.baimsg.resource.R.dimen.loading)
            .cancelOnTouchOutside(false).cancelable(false).customView(R.layout.dialog_loading)
    }

    private val contactAdapter by lazy {
        ContactAdapter()
    }

    private lateinit var tvCount: TextView

    override fun initView() {
        binding.ryContacts.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = contactAdapter
            val footerView = View.inflate(requireContext(), R.layout.footer_contacts, null)
            FooterContactsBinding.bind(footerView).apply {
                contactAdapter.setFooterView(footerView)
                this@HomeFragment.tvCount = this.tvCount
            }
            val emptyView = View.inflate(requireContext(), R.layout.empty_base, null)
            EmptyBaseBinding.bind(emptyView).apply {
                contactAdapter.setEmptyView(emptyView)
                tvTip.text = "您还没有联系人哦：）"
            }
        }

        contactAdapter.setOnItemClickListener { adapter, _, index ->
            val data = adapter.data[index] as Contacts
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    data.id
                )
            )
        }

        binding.ivSearchClear.setOnClickListener {
            binding.editSearch.text = null
        }

        binding.ivAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToEditFragment())
        }

        binding.editSearch.apply {
            addTextChangedListener {
                binding.ivSearchClear.show(it.toString().isNotBlank())
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initLiveData() {
        repeatOnLifecycleStarted {
            homeViewModel.observeContacts.collectLatest {
                contactAdapter.setList(it)
                tvCount.text = "${it.size}位联系人"
                binding.editSearch.hint = "搜索${it.size}位联系人"
            }
        }

        repeatOnLifecycleStarted {
            homeViewModel.observeMessage.collectLatest {
                it ?: return@collectLatest
                show(it, onDismiss = homeViewModel::clearMessage)
            }
        }
    }

    override fun onPause() {
        binding.editSearch.hideKeyboard()
        super.onPause()
    }
}