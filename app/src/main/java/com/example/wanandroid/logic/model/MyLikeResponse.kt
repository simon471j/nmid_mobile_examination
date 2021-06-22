package com.example.wanandroid.logic.model


data class MyLikeResponse(
    val data: DataBean,
    val errorCode: Int,
    val errorMsg: String
) {

    data class DataBean(
        val curPage: Int,
        val datas: List<DatasBean>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    ) {

        data class DatasBean(
            val author: String,
            val chapterId: Int,
            val chapterName: String,
            val courseId: Int,
            val desc: String,
            val envelopePic: String,
            val id: Int,
            val link: String,
            val niceDate: String,
            val origin: String,
            val originId: Int,
            val publishTime: String,
            val title: String,
            val userId: Int,
            val visible: Int,
            val zan: Int
        )
    }
}