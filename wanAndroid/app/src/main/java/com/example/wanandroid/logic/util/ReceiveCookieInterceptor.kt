package com.example.wanandroid.logic.util

import com.example.wanandroid.logic.dao.CookieDao
import okhttp3.Interceptor
import okhttp3.Response

/**
 * cookie拦截器
 */
class ReceiveCookieInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.headers("Set-Cookie").isNotEmpty()) {
            val cookieSet: HashSet<String> = HashSet()
            for (header in response.headers("Set-Cookie")){
                cookieSet.add(header)
            }
            CookieDao.saveCookie(cookieSet)
        }
        return response
    }
}