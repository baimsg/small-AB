package com.baimsg.contact.fragment.edit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


/**
 * Create by Baimsg on 2023/6/16
 *
 **/
@HiltViewModel
class EditViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val contactsDao: ContactsDao,
) : ViewModel() {
    private val id = stateHandle.get<Long>("id")

    private val _message: MutableStateFlow<String?> = MutableStateFlow(null)

    val observeMessage: StateFlow<String?> = _message

    private val _contact: MutableStateFlow<Contacts> = MutableStateFlow(Contacts(0))

    val observeContact: StateFlow<Contacts> = _contact

    init {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                contactsDao.observeEntriesById("$id").collectLatest {
                    it ?: return@collectLatest
                    delay(200)
                    _contact.value = it
                }
            }.onFailure {
                _message.value = "获取数据失败${it.message}"
            }
        }
    }

    fun updateHeader(
        bytes: ByteArray?,
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            bytes ?: return@launch
            val photo = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            val smallBmp = setScaleBitmap(photo, 2)
            val outputStream = ByteArrayOutputStream()
            smallBmp?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val base64Image = String(Base64.encode(outputStream.toByteArray(), Base64.URL_SAFE))
            _contact.value = _contact.value.copy(header = base64Image)
            photo?.recycle()
            smallBmp?.recycle()
        }.onFailure {
            _message.value = "解析头像失败！"
        }
    }

    fun updateName(name: String) {
        _contact.value = _contact.value.copy(name = name)
    }

    fun updateNumber(number: String) {
        _contact.value = _contact.value.copy(number = number)
    }

    fun updateEmail(email: String) {
        _contact.value = _contact.value.copy(email = email)
    }

    fun updateAddress(address: String) {
        _contact.value = _contact.value.copy(address = address)
    }

    fun updateDescribe(describe: String) {
        _contact.value = _contact.value.copy(describe = describe)
    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        with(observeContact.value) {
            if (name.isEmpty()) _message.value = "必须输入姓名哦):"
            else if (number.isEmpty()) _message.value = "必须输入手机号码):"
            else {
                runCatching {
                    if (contactsDao.countById(id = "$id") > 0) {
                        contactsDao.update(this)
                        _message.value = "更新成功(:"
                    } else {
                        contactsDao.insert(this)
                        _message.value = "添加成功(:"
                    }
                }.onFailure {
                    _message.value = "保存失败${it.message}):"
                }
            }
        }
    }

    private fun setScaleBitmap(photo: Bitmap?, scale: Int): Bitmap? {
        if (photo != null) {
            val smallBitmap: Bitmap? = zoomBitmap(photo, photo.width / scale, photo.height / scale)
            //释放原始图片占用的内存，防止out of memory异常发生
            photo.recycle()
            return smallBitmap
        }
        return null
    }

    private fun zoomBitmap(
        bitmap: Bitmap,
        width: Int,
        height: Int,
    ): Bitmap? {
        val w = bitmap.width
        val h = bitmap.height
        val matrix = Matrix()
        val scaleWidth = width.toFloat() / w
        val scaleHeight = height.toFloat() / h
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true)
    }

    fun clearMessage() {
        _message.value = null
    }
}