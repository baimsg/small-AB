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
    override fun getIdentifier(): String = "$id"
}