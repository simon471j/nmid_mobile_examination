package com.example.wanandroid.ui.home_page.banner

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class MyBannerAdapter(imageUrls: List<String>) : BannerAdapter<String, MyBannerAdapter.ImageHolder>(imageUrls) {


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageHolder {
        val imageView = ImageView(parent!!.context)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = params
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        BannerUtils.setBannerRound(imageView, 0f)
        return ImageHolder(imageView)
    }

    override fun onBindView(holder: ImageHolder, data: String, position: Int, size: Int) {
        Glide.with(holder.itemView)
            .load(data)
            .into(holder.imageView)
        holder.imageView.setOnClickListener {

        }
    }


    class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view as ImageView
    }

}