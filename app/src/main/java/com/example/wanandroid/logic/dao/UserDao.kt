package com.example.wanandroid.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.wanandroid.wanAndroidApplication

/**
 * 使用sharedPreferences存储用户保存登录账号和密码的操作
 */
object UserDao {
    fun saveUserInfo(username: String, password: String) {
        sharedPreferences().edit {
            putString("username", username)
            putString("password", password)
        }
    }

    fun clearUserInfo() = sharedPreferences().edit().clear().commit()

    fun getPassword() =
        sharedPreferences().getString("password", "")


    fun getUsername() =
        sharedPreferences().getString("username", "")


    fun isUserSaved() =
        sharedPreferences().contains("username").and(sharedPreferences().contains("password"))

    private fun sharedPreferences() =
        wanAndroidApplication.context.getSharedPreferences("wanAndroidUser", Context.MODE_PRIVATE)

}