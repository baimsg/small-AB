package com.chat.honey.fragment.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.chat.data.model.ContactItem
import com.chat.honey.R
import com.chat.honey.adapter.ContactAdapter
import com.chat.honey.base.BaseFragment
import com.chat.honey.databinding.EmptyBaseBinding
import com.chat.honey.databinding.FooterContactsBinding
import com.chat.honey.databinding.FragmentHomeBinding
import com.chat.honey.util.extensions.repeatOnLifecycleStarted
import com.chat.honey.util.extensions.show
import com.zcy.pudding.Pudding
import kotlinx.coroutines.flow.collectLatest

/**
 * Create by Baimsg on 2022/8/11
 *
 **/
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val loading by lazy {
        MaterialDialog(requireContext()).maxWidth(R.dimen.loading).cancelOnTouchOutside(false)
            .cancelable(false).customView(R.layout.dialog_loading)
    }

    private val contactAdapter by lazy {
        ContactAdapter()
    }

    private lateinit var tvCount: TextView


    @SuppressLint("InlinedApi")
    private val projection: Array<out String> = arrayOf(
        ContactsContract.Profile._ID,
        ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.Profile.PHOTO_THUMBNAIL_URI,
    )

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
            val data = adapter.data[index] as ContactItem
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    data.id
                )
            )
        }

        binding.editSearch.apply {
            addTextChangedListener {
                binding.ivSearchClear.show(it.toString().isNotBlank())
            }
        }

        binding.ivSearchClear.setOnClickListener {
            binding.editSearch.text = null
        }
    }

    override fun initData() {
        with(requireActivity()) {
            val profileCursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null
            )
            homeViewModel.syncContact(profileCursor)
        }
    }

    override fun initLiveData() {
        repeatOnLifecycleStarted {
            homeViewModel.observeContacts.collectLatest {
                contactAdapter.data = it.toMutableList()
                tvCount.text = "${it.size}位联系人"
                binding.editSearch.hint = "搜索${it.size}位联系人"
            }
        }
    }

    private fun show(text: String? = null, @StringRes textRes: Int? = null) {
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
        }.show()
    }
}