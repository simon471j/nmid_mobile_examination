package com.example.wanandroid.ui.home_page.article

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

class LikeViewModel:ViewModel() {
    private val idLike = MutableLiveData<Int>()
    private val idDislike = MutableLiveData<Int>()
    init {
        idLike.value = 0
        idDislike.value = 0
    }
    val likeLiveData = Transformations.switchMap(idLike) { id ->
        Repository.likeArticle(id)
    }
    val dislikeLiveData = Transformations.switchMap(idDislike) { id ->
        Repository.dislikeArticle(id)
    }



    fun like(id:Int) {
        Log.d("ArticleViewModel", "refreshArticle: ArticleViewModel is called")
        this.idLike.value = id
    }

    fun dislike(id:Int){
        idDislike.value = id
    }
}