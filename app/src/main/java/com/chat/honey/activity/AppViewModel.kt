package com.chat.honey.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chat.base.util.extensions.logE
import com.chat.data.api.BaseEndpoints
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

    val data = "        {\n" +
            "            \"data\": [\n" +
            "                {\n" +
            "                    \"alias\": \"xxxx\",\n" +
            "                    \"number\": 18562354354,\n" +
            "                    \"tyep\": \"abc\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alias\": \"xxxx\",\n" +
            "                    \"number\": 18562354355,\n" +
            "                    \"tyep\": \"abc\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alias\": \"xxxx\",\n" +
            "                    \"number\": 18562354356,\n" +
            "                    \"tyep\": \"abc\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }"

    fun submitBook() {
        viewModelScope.launch {
            try {
                val info =
                    baseEndpoints.addBook(data.toRequestBody("text/json; charset=utf-8".toMediaTypeOrNull()))
                logE(info)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}