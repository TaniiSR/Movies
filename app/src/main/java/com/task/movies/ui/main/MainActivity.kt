package com.task.movies.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import com.task.movies.R
import com.task.movies.databinding.ActivityDashboardBinding
import com.task.movies.ui.main.pageradapter.DashboardPagerAdaptor
import com.task.movies.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityDashboardBinding, IMainVM>() {
    override val viewModel: IMainVM by viewModels<MainVM>()
    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPager()
        mViewBinding.bottomNavigationView.setOnItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
        mViewBinding.bottomNavigationView.selectedItemId = R.id.dashboard_nav_watch
    }

    private fun setupPager() {
        val adapter = DashboardPagerAdaptor(this)
        mViewBinding.mainViewPager.adapter = adapter
        with(mViewBinding.mainViewPager) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }
        mViewBinding.mainViewPager.isUserInputEnabled = false
        mViewBinding.mainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        mViewBinding.mainViewPager.registerOnPageChangeCallback(pageChanger)
    }


    private val mOnNavigationItemSelectedListener =
        object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.dashboard_nav_home -> {
                        mViewBinding.mainViewPager.setCurrentItem(0, false)
                        return true
                    }
                    R.id.dashboard_nav_watch -> {
                        mViewBinding.mainViewPager.setCurrentItem(1, false)
                        return true
                    }

                    R.id.dashboard_nav_media -> {
                        mViewBinding.mainViewPager.setCurrentItem(2, false)
                        return true
                    }
                    R.id.dashboard_nav_more -> {
                        mViewBinding.mainViewPager.setCurrentItem(3, false)
                        return true
                    }
                    else -> false
                }
            }
        }

    private val pageChanger = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewBinding.mainViewPager.unregisterOnPageChangeCallback(pageChanger)
    }


    override fun onClick(id: Int) {
        when (id) {
            //
        }
    }

}
