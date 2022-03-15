package com.task.movies.ui.main.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.task.movies.ui.main.fragments.dashboard.DashboardFragment
import com.task.movies.ui.main.fragments.media.MediaFragment
import com.task.movies.ui.main.fragments.more.MoreFragment
import com.task.movies.ui.main.fragments.watch.WatchFragment

class DashboardPagerAdaptor(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {
    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        0 to {
            DashboardFragment()
        },
        1 to {
            WatchFragment()
        },
        2 to {
            MediaFragment()
        },
        3 to {
            MoreFragment()
        }
    )

    override fun getItemCount() = fragmentsCreators.size
    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}