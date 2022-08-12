package com.chat.data.api

import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Create by Baimsg on 2022/7/1
 *
 **/
interface BaseEndpoints {

    /**
     * @param baseUrl API地址
     * @param uid 用户id
     * @param sign appKey
     * @param timestamp 时间戳
     */
    @POST("web/addbook")
    suspend fun addBook(
        @Body body: RequestBody
    ): String
}