package com.example.wanandroid.logic.util

import okhttp3.OkHttpClient

/**
 * 构建网络请求时的client 拦截或者添加cookie
 */
object CookieUtil {
    private val builder = OkHttpClient.Builder()
    fun getReceiveInterceptor(): OkHttpClient = builder.addInterceptor(ReceiveCookieInterceptor()).build()
    fun getAddInterceptor(): OkHttpClient = builder.addInterceptor(AddCookieInterceptor()).build()
}