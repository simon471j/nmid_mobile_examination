package com.example.wanandroid.ui.home_page.banner

import androidx.lifecycle.ViewModel
import com.example.wanandroid.logic.Repository

class BannerViewModel : ViewModel() {
    var bannerLiveData = Repository.searchBanner()
    fun searchBanner(){
        bannerLiveData = Repository.searchBanner()
    }
}