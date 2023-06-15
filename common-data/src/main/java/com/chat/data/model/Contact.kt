package com.chat.data.model

import kotlinx.serialization.Serializable

/**
 * Create by Baimsg on 2022/8/14
 *
 **/
@Serializable
data class Contacts(
    val data: List<ContactItem>,
)

@Serializable
data class ContactItem(
    val id: Long,
    val name: String,
    val number: String,
    val photoThumbUri: String,
)