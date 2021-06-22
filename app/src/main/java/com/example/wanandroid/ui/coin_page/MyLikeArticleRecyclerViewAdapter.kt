package com.example.wanandroid.ui.coin_page

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.ui.home_page.article.ArticleRecyclerViewAdapter.ViewHolder
import com.example.wanandroid.logic.model.ArticleResponse
import com.example.wanandroid.logic.model.MyLikeResponse
import com.example.wanandroid.ui.Webview
import com.example.wanandroid.ui.home_page.article.ArticleRecyclerViewAdapter
import com.example.wanandroid.ui.home_page.article.LikeViewModel
import com.example.wanandroid.wanAndroidApplication

/**
 * 文章的recyclerview的适配器
 */
class MyLikeArticleRecyclerViewAdapter(
    private var datas: ArrayList<MyLikeResponse.DataBean.DatasBean>,
    val viewLifecycleOwner: LifecycleOwner,
    viewModelStoreOwner: ViewModelStoreOwner
) :
    RecyclerView.Adapter<MyLikeArticleRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context
    val viewModel by lazy { ViewModelProvider(viewModelStoreOwner).get(LikeViewModel::class.java) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.view
        val author: TextView = view.findViewById(R.id.tv_author)
        val publishDate: TextView = view.findViewById(R.id.tv_date)
        val title: TextView = view.findViewById(R.id.tv_title)
        val description: TextView = view.findViewById(R.id.tv_description)

        if (datas[position].author != "")
            author.text = datas[position].author
        else {
            author.text = ""
        }
        publishDate.text = datas[position].niceDate
        title.text = datas[position].title
        description.text = datas[position].desc
        view.setOnClickListener {
            val intent = Intent(context, Webview::class.java)
            intent.putExtra("url", datas[position].link)
            context.startActivity(intent)

        }
        view.setOnLongClickListener {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.longclick_article_menu, popupMenu.menu);

//            弹出式菜单的菜单项点击事件
            popupMenu.setOnMenuItemClickListener { it ->
                when (it.itemId) {
                    R.id.dislike -> {
                        viewModel.dislikeLiveData.observe(viewLifecycleOwner, Observer {
                            val result = it.getOrNull()
                            when {
                                result == null -> Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show()
                                result.errorCode == 0 -> {
                                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show()
                                    datas.removeAt(position)
                                    notifyDataSetChanged()
                                }
                                else -> Log.d("dislike", "dislike: 不合法操作")
                            }
                        })
                        viewModel.dislike(datas[position].originId)
                    }

                    else -> Log.d("popupMenu", "未点击事件")
                }

                true
            }

            popupMenu.gravity = Gravity.RIGHT
            popupMenu.show()
            true
        }
    }


    override fun getItemCount() = datas.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(datas: ArrayList<MyLikeResponse.DataBean.DatasBean>) {
        this.datas = datas
    }
}

