package com.example.wanandroid.logic.util

import android.util.Log
import com.example.wanandroid.logic.dao.CookieDao
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * cookie添加器
 */
class AddCookieInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = CookieDao.getCookie()
        if (cookies!=null){
            for (cookie in cookies){
                builder.addHeader("Cookie",cookie)
            }
        }
        return chain.proceed(builder.build())
    }

}