package com.example.wanandroid.logic.network

import android.app.Service
import android.util.Log
import com.example.wanandroid.logic.network.wanAndroidNetwork.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 所有的网络在网络层的使用
 */

object wanAndroidNetwork {
    private val bannerService = ServiceCreator.create<BannerService>()
    private val articleService = ServiceCreator.create<ArticleService>()
    private val treeService = ServiceCreator.create<TreeService>()
    private val articleInTreeService = ServiceCreator.create<ArticleInTreeService>()
    private val loginService = ServiceCreator.create<LoginService>()
    private val getCoinService = ServiceCreator.create<CoinService>()
    private val likeArticleService = ServiceCreator.create<LikeArticleService>()
    private val myLikeService = ServiceCreator.create<MyLikeService>()
    private val dislikeService = ServiceCreator.create<DislikeService>()

    suspend fun searchBanner() = bannerService.getBanner().await()
    suspend fun searchArticle(page: Int) = articleService.searchArticle(page).await()
    suspend fun searchTree() = treeService.searchTree().await()
    suspend fun searchArticleInTreeService(page: String, cid: String) =
        articleInTreeService.searchArticleInTree(page, cid).await()

    suspend fun login(username: String, password: String) =
        loginService.login(username, password).await()


    suspend fun register(username: String, password: String, repassword: String) =
        loginService.register(username, password, repassword).await()

    suspend fun getCoinInfo(

    ) = getCoinService.getCoin(

    ).await()

    suspend fun likeArticle(id: Int) = likeArticleService.likeArticle(id).await()
    suspend fun searchMyLike(page: Int) = myLikeService.searchMyLike(page).await()
    suspend fun dislikeService(id: Int) = dislikeService.dislikeArticle(id).await()

    private suspend fun <T> Call<T>.await(): T {

        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        it.resume(body)
                        Log.d("请求结果", " ${body}}")
                    } else
                        it.resumeWithException(RuntimeException("返回数据为空"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                    Log.d("请求结果", t.toString())
                }
            })

        }
    }
}