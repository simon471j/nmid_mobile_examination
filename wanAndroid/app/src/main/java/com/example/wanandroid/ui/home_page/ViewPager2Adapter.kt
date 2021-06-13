package com.example.wanandroid.ui.home_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wanandroid.ui.home_page.HomePageFragment

class ViewPager2Adapter(
    val fragmentManager: FragmentManager,
    val lifecycle: Lifecycle,
    val fragments: List<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    //    var fragments = listOf<Fragment>(HomePageFragment(), HomePageFragment())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}