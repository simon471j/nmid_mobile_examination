package com.example.wanandroid.ui.tree

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wanandroid.R
import com.example.wanandroid.logic.model.ArticleResponse
import com.example.wanandroid.ui.home_page.article.ArticleRecyclerViewAdapter
import com.example.wanandroid.wanAndroidApplication

class ArticleInTree : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(ArticleInTreeViewModel::class.java) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_fragment)
        val cid = intent.getStringExtra("id")
        var page = 0
        val recyclerView = findViewById<RecyclerView>(R.id.rv_article)
        val layoutManager = LinearLayoutManager(this)
        val toolbar: Toolbar = findViewById(R.id.fragment_article_toolbar)
        toolbar.visibility = View.VISIBLE
        recyclerView.layoutManager = layoutManager
        //      存储网络请求的结果
        val articleResponseContainer = ArrayList<ArticleResponse.DataBean.DatasBean>()
        val recyclerViewAdapter = ArticleRecyclerViewAdapter(articleResponseContainer,this,this)
        recyclerView.adapter = recyclerViewAdapter

        viewModel.articleInTree.observe(this, Observer
        {
            val result = it.getOrNull()
            if (result != null) {

                //      更新网络请求的结果
                articleResponseContainer.addAll(result.data.datas)
                recyclerViewAdapter.setData(articleResponseContainer)
                recyclerViewAdapter.notifyDataSetChanged()

                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(
                        recyclerView: RecyclerView,
                        newState: Int
                    ) {
                        super.onScrollStateChanged(recyclerView, newState)
                        val lastVisibleItemPosition =
                            layoutManager.findLastVisibleItemPosition()
                        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == recyclerViewAdapter.itemCount - 1) {
                            page += 1
                            viewModel.loadArticleInTree("$page", cid!!)
                        }
                    }
                })
            } else {
                Log.d("Article", "onActivityCreated: 没有找到文章资源")
                Toast.makeText(wanAndroidApplication.context, "找不到文章数据", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.loadArticleInTree("0", cid.toString())
    }
}
