package com.task.movies.ui.main.fragments.dashboard

import androidx.fragment.app.viewModels
import com.task.movies.R
import com.task.movies.databinding.FragmentDashboardBinding
import com.task.movies.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, IDashboard>(R.layout.fragment_dashboard) {
    override val viewModel: IDashboard by viewModels<DashboardVM>()
    override fun getViewBinding(): FragmentDashboardBinding =
        FragmentDashboardBinding.inflate(layoutInflater)

    override fun onClick(id: Int) {
        //
    }
}