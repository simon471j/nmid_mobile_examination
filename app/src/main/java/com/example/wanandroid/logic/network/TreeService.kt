package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.TreeResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * 体系中的流式布局的网络请求
 */
interface TreeService {
    @GET("tree/json")
    fun searchTree(): Call<TreeResponse>
}