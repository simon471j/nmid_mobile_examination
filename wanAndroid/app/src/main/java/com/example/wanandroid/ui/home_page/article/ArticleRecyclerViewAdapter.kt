package com.example.wanandroid.ui.home_page.article

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.ui.home_page.article.ArticleRecyclerViewAdapter.ViewHolder
import com.example.wanandroid.logic.model.ArticleResponse
import com.example.wanandroid.ui.Webview

/**
 * 文章的recyclerview的适配器
 */
class ArticleRecyclerViewAdapter(private var datas: ArrayList<ArticleResponse.DataBean.DatasBean> ) :
    RecyclerView.Adapter<ViewHolder>() {

    lateinit var context: Context

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
    }

    override fun getItemCount() = datas.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(datas: ArrayList<ArticleResponse.DataBean.DatasBean>){
        this.datas = datas
    }
}

