package com.chat.honey.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.data.api.BaseEndpoints
import com.chat.data.model.ContactItem
import com.chat.data.model.Contacts
import com.chat.data.model.JSON
import com.chat.honey.fragment.login.SubmitContactViewState
import com.chat.honey.type.ExecutionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

/**
 * Create by Baimsg on 2022/8/12
 *
 **/
@HiltViewModel
class AppViewModel @Inject constructor(
    private val baseEndpoints: BaseEndpoints
) : ViewModel() {

    private val _submitViewState by lazy {
        MutableStateFlow(SubmitContactViewState.EMPTY)
    }

    val observeSubmitContactViewState: StateFlow<SubmitContactViewState> = _submitViewState

    private val contacts: List<ContactItem>
        get() =
            _submitViewState.value.contacts

    fun addContact(contactItem: ContactItem) {
        _submitViewState.apply {
            value = value.copy(contacts = value.contacts.toMutableList().apply {
                add(contactItem)
            }, executionStatus = ExecutionStatus.LOADING)
        }
    }

    fun submitBook() {
        viewModelScope.launch {
            _submitViewState.apply {
                delay(350)
                value = try {
                    val info =
                        baseEndpoints.addBook(
                            JSON.encodeToString(
                                Contacts.serializer(),
                                Contacts(contacts)
                            ).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                        )
                    value.copy(executionStatus = ExecutionStatus.SUCCESS, msg = "配置加载成功(:")
                } catch (e: Exception) {
                    value.copy(executionStatus = ExecutionStatus.FAIL, msg = "配置加载失败):")
                }
                delay(300)
                value = value.copy(executionStatus = ExecutionStatus.UNKNOWN, msg = "")
            }

        }
    }

}