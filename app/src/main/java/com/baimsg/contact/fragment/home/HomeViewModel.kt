package com.baimsg.contact.fragment.home

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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contactsDao: ContactsDao,
) : ViewModel() {

    private var _allContacts: List<Contacts> = mutableListOf()

    private val _message: MutableStateFlow<String?> = MutableStateFlow(null)

    val observeMessage: StateFlow<String?> = _message.asStateFlow()

    private val _contacts: MutableStateFlow<List<Contacts>> = MutableStateFlow(mutableListOf())

    val observeContacts: StateFlow<List<Contacts>> = _contacts.asStateFlow()

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            contactsDao.observeEntries().collectLatest {
                _allContacts = it
                delay(200)
                _contacts.value = _allContacts
            }
        }.onFailure {
            _message.value = "获取联系人列表失败${it.message}"
        }
    }

    fun clearMessage() {
        _message.value = null
    }

}