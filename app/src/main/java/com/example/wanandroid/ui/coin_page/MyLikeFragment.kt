package com.example.wanandroid.ui.coin_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.logic.model.MyLikeResponse.DataBean.DatasBean
import com.example.wanandroid.wanAndroidApplication

class MyLikeFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this).get(MyLikeViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val toolbar = requireView().findViewById<Toolbar>(R.id.fragment_article_toolbar)
        val toolbarTitle: TextView = requireView().findViewById(R.id.toolbar_title)
        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_article)
        val nestedScrollView: NestedScrollView =
            requireActivity().findViewById(R.id.nested_scroll_view_my_like)
        val layoutManager = LinearLayoutManager(activity)

        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.isNestedScrollingEnabled = false
        toolbar.visibility = View.VISIBLE
        toolbarTitle.text = "我的收藏"

//      存储网络请求的结果
        val articleResponseContainer = ArrayList<DatasBean>()

        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer
        {
            val result = it.getOrNull()
            if (result != null) {

                //      更新网络请求的结果
                articleResponseContainer += result.data.datas
                val recyclerViewAdapter =
                    MyLikeArticleRecyclerViewAdapter(articleResponseContainer, this, this)
                recyclerView.adapter = recyclerViewAdapter
                recyclerViewAdapter.notifyDataSetChanged()


//                NestedScrollView滑动事件 超出指定范围则加载数据


                nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                    if (scrollY == (v!!.getChildAt(0).measuredHeight - v.measuredHeight)) {
                        viewModel.refreshArticle()//调用刷新控件对应的加载更多方法
                    }
                })

            } else {
                Log.d("Article", "onActivityCreated: 没有找到文章资源")
                Toast.makeText(wanAndroidApplication.context, "找不到文章数据", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.refreshArticle()
    }
}