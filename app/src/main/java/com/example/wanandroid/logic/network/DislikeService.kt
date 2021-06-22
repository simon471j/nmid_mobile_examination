package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.DislikeResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface DislikeService {
    @POST("lg/uncollect_originId/{id}/json")
    fun dislikeArticle(@Path("id") id: Int):Call<DislikeResponse>
}
