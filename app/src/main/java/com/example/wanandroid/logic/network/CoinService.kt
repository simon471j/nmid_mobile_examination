package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.model.CoinResponse
import retrofit2.Call
import retrofit2.http.*

interface CoinService {

    /**
     * 导航栏的网络请求 包括用户名 积分 排行和等级
     */
    @GET("lg/coin/userinfo/json")
    fun getCoin(): Call<CoinResponse>
}