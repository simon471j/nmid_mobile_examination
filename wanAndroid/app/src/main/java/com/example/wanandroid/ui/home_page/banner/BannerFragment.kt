package com.example.wanandroid.ui.home_page.banner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wanandroid.R
import com.example.wanandroid.ui.Webview
import com.example.wanandroid.wanAndroidApplication
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener

/**
 * 首页轮播图
 */
class BannerFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(BannerViewModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.banner, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val banner = view?.findViewById(R.id.banner) as Banner<String, MyBannerAdapter>


//        获取到banner的信息之后更新图片
        viewModel.bannerLiveData.observe(viewLifecycleOwner, Observer {
            val result = it.getOrNull()
            if (result != null) {
                val pathList: ArrayList<String> = ArrayList()
                pathList.apply {
                    for (databean in result.data) {
                        add(databean.imagePath)
                    }
                }
                val bannerAdapter = MyBannerAdapter(pathList)
                banner.apply {
                    addBannerLifecycleObserver(viewLifecycleOwner)
                    setBannerRound(0f)
                    setAdapter(bannerAdapter)
                }

//                banner的点击事件网页跳转
                bannerAdapter.setOnBannerListener(OnBannerListener { data, position ->
                    val intent = Intent(context,Webview::class.java)
                    intent.putExtra("url",result.data[position].url)
                    startActivity(intent)
                })


            } else
                Toast.makeText(wanAndroidApplication.context, "找不到banner资源", Toast.LENGTH_SHORT)
                    .show()
        })
        viewModel.searchBanner()


    }

}