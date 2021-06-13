package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 首页页面文章
 */
interface ArticleService {
    @GET("article/list/{page}/json")
    fun searchArticle(@Path("page") page: Int): Call<ArticleResponse>
}