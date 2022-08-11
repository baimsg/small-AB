package com.chat.data.api

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
    @FormUrlEncoded
    @POST("/api/user/getFakeUid")
    suspend fun postUserDetail(
        @HeaderMap headers: Map<String, String>,
        @FieldMap fields: Map<String, String>
    ): String
}