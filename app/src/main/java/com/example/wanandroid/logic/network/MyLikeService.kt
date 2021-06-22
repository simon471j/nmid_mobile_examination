package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.MyLikeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyLikeService {
    @GET("lg/collect/list/{page}/json")
    fun searchMyLike(@Path("page") page: Int): Call<MyLikeResponse>
}