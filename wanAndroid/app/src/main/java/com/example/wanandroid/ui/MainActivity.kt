package com.example.wanandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid.R
import com.example.wanandroid.ui.coin_page.PersonalPageFragment
import com.example.wanandroid.ui.home_page.HomePageFragment
import com.example.wanandroid.ui.home_page.ViewPager2Adapter
import com.example.wanandroid.ui.tree.TreeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val viewPager2: ViewPager2 = findViewById(R.id.viewPager2)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        为drawerLayout设置监听按钮
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawerOpen,
            R.string.drawerClose
        )
        actionBarDrawerToggle.syncState()
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        fragment的集合
        val fragments = listOf<Fragment>(HomePageFragment(), TreeFragment())
//        为viewPager2设置适配器
        viewPager2.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle, fragments)
//        tabLayout的标题
        val titles = listOf("首页", "体系")


//        将tabLayout和viewPager2绑定在一起
/*        TabLayoutMediator(tabLayout,viewPager2,TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = titles[position]
        }).attach()
        下面为简化的代码
*/
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()


    }

}