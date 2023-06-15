package com.chat.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.chat.data.entities.Contacts
import kotlinx.coroutines.flow.Flow

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@Dao
abstract class ContactsDao : BaseDao<Contacts>() {
    @Transaction
    @Query("DELETE FROM contacts WHERE id=:id")
    abstract override suspend fun deleteById(id: String)
    

    @Query("DELETE FROM contacts")
    abstract override suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM contacts")
    abstract override fun entries(): List<Contacts>

    @Transaction
    @Query("SELECT * FROM contacts")
    abstract override fun observeEntries(): Flow<List<Contacts>>

    @Transaction
    @Query("SELECT * FROM contacts LIMIT :count OFFSET :offset")
    abstract override fun entries(count: Int, offset: Int): List<Contacts>

    @Transaction
    @Query("SELECT * FROM contacts LIMIT :count OFFSET :offset")
    abstract override fun observeEntries(count: Int, offset: Int): Flow<List<Contacts>>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    abstract override fun entriesById(id: String): Contacts

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    abstract override fun observeEntriesById(id: String): Flow<Contacts>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id IN (:ids)")
    abstract override fun entriesByIds(ids: List<String>): List<Contacts>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id IN (:ids)")
    abstract override fun observeEntriesByIds(ids: List<String>): Flow<List<Contacts>>

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    abstract override fun entriesByIdNullable(id: String): Contacts?

    @Transaction
    @Query("SELECT * FROM contacts WHERE id = :id")
    abstract override fun observeEntriesByIdNullable(id: String): Flow<Contacts?>

    @Query("SELECT COUNT(*) FROM contacts")
    abstract override suspend fun count(): Int

    @Query("SELECT COUNT(*) FROM contacts")
    abstract override fun observeCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM contacts WHERE id=:id")
    abstract override suspend fun countById(id: String): Int

    @Query("SELECT COUNT(*) FROM contacts WHERE id=:id")
    abstract override fun observeCountById(id: String): Flow<Int>
}