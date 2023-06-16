package com.baimsg.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@Serializable
@Entity(tableName = "contacts")
data class Contacts(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val header: String? = null,
    val name: String = "",
    val number: String = "",
    val email: String? = null,
    val address: String? = null,
    val describe: String? = null,
) : BaseEntity {
    val emailText: String
        get() = if (email.isNullOrBlank()) "暂时没有邮箱" else email
    val addressText: String
        get() = if (address.isNullOrBlank()) "暂时没有地址" else address
    val describeText: String
        get() = if (describe.isNullOrBlank()) "暂时没有描述" else describe

    override fun getIdentifier(): String = "$id"
}