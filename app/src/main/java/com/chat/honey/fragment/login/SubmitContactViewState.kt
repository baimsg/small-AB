package com.chat.honey.fragment.login

import com.chat.data.model.ContactItem
import com.chat.honey.type.ExecutionStatus

/**
 * Create by Baimsg on 2022/8/14
 *
 **/
data class SubmitContactViewState(
    val contacts: List<ContactItem>,
    val msg: String,
    val executionStatus: ExecutionStatus
) {
    companion object {
        val EMPTY = SubmitContactViewState(
            contacts = emptyList(),
            msg = "",
            executionStatus = ExecutionStatus.UNKNOWN
        )
    }
}