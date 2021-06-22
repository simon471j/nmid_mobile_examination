package com.example.wanandroid.ui.coin_page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

class MyLikeViewModel : ViewModel() {
    private val page = MutableLiveData<Int>()

    init {
        page.value = -1
    }

    val articleLiveData = Transformations.switchMap(page) { page ->
        Repository.searchMyLike(page)
    }

    fun refreshArticle() {
        Log.d("ArticleViewModel", "refreshArticle: ArticleViewModel is called")
        val nextPage = page.value!!.plus(1)
        page.value = nextPage
    }
}