package com.chat.honey.activity

import androidx.lifecycle.ViewModel
import com.chat.data.model.ContactItem
import com.chat.honey.fragment.login.SubmitContactViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Create by Baimsg on 2022/8/12
 *
 **/
@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {

    private val _submitViewState by lazy {
        MutableStateFlow(SubmitContactViewState.EMPTY)
    }

    val observeSubmitContactViewState: StateFlow<SubmitContactViewState> = _submitViewState

    private val contacts: List<ContactItem>
        get() = _submitViewState.value.contacts

}