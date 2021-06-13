package com.example.wanandroid.logic.model

data class LoginResponse(
    val data: DataBean?,
    val errorCode: Int,
    val errorMsg: String
) {

    data class DataBean(
        val admin: Boolean,
        val chapterTops: List<Any?>,
        val coinCount: Int,
        val collectIds: List<Any?>,
        val email: String,
        val icon: String,
        val id: Int,
        val nickname: String,
        val password: String,
        val publicName: String,
        val token: String,
        val type: Int,
        val username: String
    )
}