package com.example.wanandroid.logic.dao

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import com.example.wanandroid.wanAndroidApplication

/**
 * 使用sharedPreferences存储cookie持久化数据
 */
object CookieDao {

    fun saveCookie(cookieSet: HashSet<String>) {
        sharedPreferences().edit { putStringSet("cookie", cookieSet) }
        Log.d("headerAction", "saveCookie: ${getCookie().toString()}")
    }

    fun getCookie(): MutableSet<String>? = sharedPreferences().getStringSet("cookie", null)

    private fun sharedPreferences() =
        wanAndroidApplication.context.getSharedPreferences("cookie", Context.MODE_PRIVATE)

}