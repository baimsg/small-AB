package com.baimsg.data.db.dao

import com.baimsg.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
@InstallIn(SingletonComponent::class)
@Module
class DaoModule {
    @Provides
    fun createContactsDao(appDatabase: AppDatabase): ContactsDao = appDatabase.createContactsDao()
}