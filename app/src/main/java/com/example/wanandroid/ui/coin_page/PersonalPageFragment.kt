package com.example.wanandroid.ui.coin_page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.wanandroid.R
import com.example.wanandroid.logic.Repository
import com.google.android.material.card.MaterialCardView

/**
 * 左侧导航栏的页面
 */
class PersonalPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val coinCnt: TextView = requireView().findViewById(R.id.tv_coin_count)
        val username: TextView = requireView().findViewById(R.id.tv_nav_username)
        val rank: TextView = requireView().findViewById(R.id.tv_nav_rank)
        val level: TextView = requireView().findViewById(R.id.tv_nav_level)
        val like: MaterialCardView = requireView().findViewById(R.id.nav_like)
        Repository.getCoinInfo()
            .observe(viewLifecycleOwner,
                Observer {
                    val result = it.getOrNull()
                    if (result != null) {
                        coinCnt.text = result.data.coinCount.toString()
                        username.text = result.data.username
                        rank.text = "排名：${result.data.rank}"
                        level.text = "等级：${result.data.level}"
                    } else {
                        Toast.makeText(requireContext(), "没有查询到用户信息", Toast.LENGTH_SHORT).show()
                        Log.d("请求结果", "金币数据为空 ")
                    }
                })

        like.setOnClickListener {
            val intent = Intent(requireContext(), MyLikeArticleActivity::class.java)
            startActivity(intent)

        }

    }
}