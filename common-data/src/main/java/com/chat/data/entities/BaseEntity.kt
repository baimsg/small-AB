package com.chat.data.entities

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
interface BaseEntity {


    /**
     * 获取主键
     */
    fun getIdentifier(): String
}