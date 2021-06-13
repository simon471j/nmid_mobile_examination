package com.example.wanandroid.ui.home_page.article

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

/**
 * 实现监听滑动事件进行article的页数增加
 */
class ArticleViewModel : ViewModel() {
    private val page = MutableLiveData<Int>()
    init {
        page.value = 0
    }
    val articleLiveData = Transformations.switchMap(page) { page ->
        Repository.searchArticle(page)
    }

    fun refreshArticle() {
        Log.d("ArticleViewModel", "refreshArticle: ArticleViewModel is called")
        val nextPage = page.value?.plus(1)
        page.value = nextPage
    }
}