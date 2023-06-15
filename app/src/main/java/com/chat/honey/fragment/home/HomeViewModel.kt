package com.chat.honey.fragment.home

import android.database.Cursor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.data.model.ContactItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _allContacts: MutableList<ContactItem> = mutableListOf()

    private val _contacts: MutableStateFlow<MutableList<ContactItem>> =
        MutableStateFlow(mutableListOf())

    val observeContacts: StateFlow<List<ContactItem>> = _contacts

    fun syncContact(contactCursor: Cursor?) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            contactCursor?.let { cursor ->
                while (cursor.moveToNext()) {
                    with(_allContacts) {
                        val id = cursor.getLong(0)
                        if (_allContacts.none { it.id == id }) {
                            add(
                                ContactItem(
                                    id = id,
                                    name = cursor.getString(1) ?: "",
                                    number = cursor.getString(2) ?: "",
                                    photoThumbUri = cursor.getString(3) ?: "",
                                )
                            )
                        }
                    }
                }
                _contacts.value = _allContacts
                cursor.close()
            }
        }
    }


}