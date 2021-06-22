package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.LikeArticleResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface LikeArticleService {

    @POST("/lg/collect/{id}/json")
    fun likeArticle(@Path("id") id: Int): Call<LikeArticleResponse>
}