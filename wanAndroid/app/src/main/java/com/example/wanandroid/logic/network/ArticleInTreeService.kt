package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 体系页面的流式布局中点击chip之后的网络请求
 */
interface ArticleInTreeService {
    @GET("article/list/{page}/json")
    fun searchArticleInTree(
        @Path("page") page: String,
        @Query("cid") cid: String
    ): Call<ArticleResponse>
}