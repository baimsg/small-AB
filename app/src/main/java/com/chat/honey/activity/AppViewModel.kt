package com.chat.honey.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.base.util.extensions.logE
import com.chat.data.api.BaseEndpoints
import com.chat.data.model.ContactItem
import com.chat.data.model.Contacts
import com.chat.data.model.JSON
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType
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

    fun submitBook() {
        viewModelScope.launch {
            val contactItems = mutableListOf<ContactItem>()
            repeat(10) {
                contactItems.add(
                    ContactItem(
                        alias = "alia->$it",
                        number = "1343464667",
                        type = "type->$it"
                    )
                )
            }
            try {
                val info =
                    baseEndpoints.addBook(
                        JSON.encodeToString(
                            Contacts.serializer(),
                            Contacts(contactItems)
                        ).toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                    )
                logE(info)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}