package com.example.wanandroid.ui.tree

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

class ArticleInTreeViewModel : ViewModel() {
    val dataArticle = MutableLiveData<PageChange>()
    val articleInTree = Transformations.switchMap(dataArticle){
        Repository.searchArticleInTree(it.page, it.cid)
    }

    init {
        loadArticleInTree("0", "60")
    }


    fun loadArticleInTree(page: String, cid: String) {
        dataArticle.value = PageChange(page,cid)
    }

    class PageChange(val page: String, val cid: String)
}