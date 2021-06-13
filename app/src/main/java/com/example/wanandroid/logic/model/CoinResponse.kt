package com.example.wanandroid.logic.model

data class CoinResponse(val data: DataBean,
                     val errorCode: Int,
                     val errorMsg: String) {

    data class DataBean(val coinCount: Int,
                        val level: Int,
                        val nickname: String,
                        val rank: String,
                        val userId: Int,
                        val username: String)
}