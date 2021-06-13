package com.example.wanandroid.logic.network

import com.example.wanandroid.logic.util.CookieUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit的服务构造器
 */
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com/"
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(CookieUtil.getReceiveInterceptor())
        .client(CookieUtil.getAddInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}