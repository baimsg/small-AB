package com.baimsg.contact.fragment.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baimsg.data.db.dao.ContactsDao
import com.baimsg.data.entities.Contacts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@HiltViewModel
class DetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    contactsDao: ContactsDao,
) : ViewModel() {
    val id = stateHandle.get<Long>("id")

    private val _contacts: MutableStateFlow<Contacts> = MutableStateFlow(Contacts(id = 0))

    val observeContacts: StateFlow<Contacts> = _contacts.asStateFlow()

    private val _message: MutableStateFlow<String?> = MutableStateFlow(null)

    val observeMessage: StateFlow<String?> = _message.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                contactsDao.observeEntriesById("$id").catch {
                    _message.value = "获取信息失败${it.message}"
                }.collectLatest {
                    delay(200)
                    _contacts.value = it
                }
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }

}