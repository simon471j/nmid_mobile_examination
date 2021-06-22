package com.example.wanandroid.logic

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import com.example.wanandroid.logic.network.wanAndroidNetwork
import com.example.wanandroid.wanAndroidApplication
import kotlinx.coroutines.Dispatchers

/**
 * 所有的网络请求封装好之后放在这里
 */
object Repository {

    fun searchBanner() = liveData(Dispatchers.IO) {
        val result = try {
            val bannerResponse = wanAndroidNetwork.searchBanner()
            Result.success(bannerResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun searchArticle(page: Int) = liveData(Dispatchers.IO) {
        val result = try {
            val articleResponse = wanAndroidNetwork.searchArticle(page)
            Result.success(articleResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun searchTree() = liveData(Dispatchers.IO) {
        val result = try {
            val treeResponse = wanAndroidNetwork.searchTree()
            Result.success(treeResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun searchArticleInTree(page: String, cid: String) = liveData(Dispatchers.IO) {
        val result = try {
            val articleInTree = wanAndroidNetwork.searchArticleInTreeService(page, cid)
            Result.success(articleInTree)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginResponse = wanAndroidNetwork.login(username, password)
            Log.d("登录请求结果", loginResponse.toString())
            Result.success(loginResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
        Log.d("登录请求结果", result.toString())

        emit(result)
    }

    fun register(username: String, password: String, repassword: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val loginResponse = wanAndroidNetwork.register(username, password, repassword)
                Result.success(loginResponse)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun getCoinInfo(

    ) = liveData(Dispatchers.IO) {
        val result = try {
            val coinInfo = wanAndroidNetwork.getCoinInfo(

            )
            Result.success(coinInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun likeArticle(id: Int) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("likeArticle", "likeArticle: 执行了")
            val likeArticleResponse = wanAndroidNetwork.likeArticle(id)
            Log.d("likeArticleResponse", likeArticleResponse.toString())
            Result.success(likeArticleResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun searchMyLike(page: Int) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("searchMyLike", "searchMyLike: 执行 page:${page}")
            val myLike = wanAndroidNetwork.searchMyLike(page)
            Result.success(myLike)
        } catch (e: Exception) {
            Log.d("searchMyLike", "$e")
            Result.failure(e)
        }
        emit(result)
    }

    fun dislikeArticle(id:Int) = liveData(Dispatchers.IO) {
        val result = try {
            val dislike = wanAndroidNetwork.dislikeService(id)
            Result.success(dislike)
        } catch (e: Exception) {
            Log.d("searchMyLike", "$e")
            Result.failure(e)
        }
        emit(result)
    }


}