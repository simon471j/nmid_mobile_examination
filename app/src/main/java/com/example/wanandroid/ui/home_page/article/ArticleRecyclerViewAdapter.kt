package com.example.wanandroid.ui.home_page.article

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
import com.example.wanandroid.ui.Webview
import com.example.wanandroid.wanAndroidApplication

/**
 * 文章的recyclerview的适配器
 */
class ArticleRecyclerViewAdapter(
    private var datas: ArrayList<ArticleResponse.DataBean.DatasBean>,
    val viewLifecycleOwner: LifecycleOwner,
    viewModelStoreOwner: ViewModelStoreOwner
) :
    RecyclerView.Adapter<ViewHolder>() {

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
            author.text = "转载自${datas[position].shareUser}"
        }
        publishDate.text = datas[position].niceShareDate
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
            popupMenu.menu.removeItem(R.id.dislike)
            //弹出式菜单的菜单项点击事件
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.like -> {
                        viewModel.likeLiveData.observe(viewLifecycleOwner, Observer {
                            val result = it.getOrNull()
                            if (result != null && result.errorCode == 0){
                                Toast.makeText(
                                    wanAndroidApplication.context,
                                    "收藏成功",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else
                                Toast.makeText(
                                    wanAndroidApplication.context,
                                    "数据为空",
                                    Toast.LENGTH_SHORT
                                ).show()
                        })
                        Log.d("like", "onBindViewHolder: 点击了收藏")
                        val result = viewModel.like(datas[position].id)

                    }
//                    R.id.dislike->

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

    fun setData(datas: ArrayList<ArticleResponse.DataBean.DatasBean>) {
        this.datas = datas
    }
}

