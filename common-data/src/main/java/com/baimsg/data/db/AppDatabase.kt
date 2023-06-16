package com.baimsg.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baimsg.data.db.converter.BaseTypeConverters
import com.baimsg.data.db.dao.ContactsDao
import com.baimsg.data.entities.Contacts

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@Database(
    version = DatabaseMigrations.DB_VERSION, entities = [Contacts::class], exportSchema = true
)
@TypeConverters(
    BaseTypeConverters::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun createContactsDao(): ContactsDao
}