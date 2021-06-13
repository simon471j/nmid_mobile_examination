package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.BannerResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * 首页轮播图的网络请求
 */
interface BannerService {
    @GET("banner/json")
    fun getBanner(): Call<BannerResponse>
}