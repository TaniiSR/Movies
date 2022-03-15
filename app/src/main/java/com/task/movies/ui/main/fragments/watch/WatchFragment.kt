package com.task.movies.ui.main.fragments.watch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.task.movies.R
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.databinding.FragmentWatchBinding
import com.task.movies.utils.base.BaseFragment
import com.task.movies.utils.base.interfaces.OnItemClickListener
import com.task.movies.utils.base.sealed.UIEvent
import com.task.movies.utils.extensions.gone
import com.task.movies.utils.extensions.observe
import com.task.movies.utils.extensions.visible
import com.task.movies.utils.uikit.toolbarview.ToolBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchFragment :
    BaseFragment<FragmentWatchBinding, IWatch>(R.layout.fragment_watch),
    ToolBarView.OnToolBarViewClickListener {
    override val viewModel: IWatch by viewModels<WatchVM>()
    override fun getViewBinding(): FragmentWatchBinding =
        FragmentWatchBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setUpRecyclerView()
        mViewBinding.tbView.setOnToolBarViewClickListener(this)
        viewModel.getUpcomingMovies(apiKey = viewModel.apiKey, false)
    }

    override fun onEndIconClicked() {
        showToast("Search Screen Open")
    }

    private fun handleRepoList(list: List<UpcomingMovie>) {
        if (!list.isNullOrEmpty()) {
            viewModel.adaptor.setList(list)
        } else setErrorView()
    }

    private fun setListener() {
        mViewBinding.btnRetry.setOnClickListener(this)
        mViewBinding.swRefresh.setOnRefreshListener {
            mViewBinding.swRefresh.isRefreshing = false
            viewModel.fetchFreshData()
        }
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.btnRetry -> viewModel.fetchFreshData()
        }
    }

    private fun setUpRecyclerView() {
        viewModel.adaptor.onItemClickListener = rvItemClickListener
        mViewBinding.recyclerView.adapter = viewModel.adaptor
    }

    private val rvItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, data: Any, pos: Int) {
            when (data) {
                is UpcomingMovie -> {
                    showToast("Detail Screen Open")
                }
            }
        }
    }

    private fun handleUiState(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.Loading -> setLoadingView()
            is UIEvent.Success -> setSuccessView()
            is UIEvent.Error -> setErrorView()
            is UIEvent.Message -> showToast(uiEvent.message)
        }
    }

    private fun setErrorView() {
        mViewBinding.errorView.visible()
        mViewBinding.recyclerView.gone()
        hideShimmerLoading()
    }

    private fun setSuccessView() {
        mViewBinding.recyclerView.visible()
        mViewBinding.errorView.gone()
        hideShimmerLoading()
    }

    private fun setLoadingView() {
        mViewBinding.errorView.gone()
        mViewBinding.recyclerView.gone()
        showShimmerLoading()
    }

    private fun showShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.visible()
        mViewBinding.shimmerLayout.shimmerFrameLayout.startShimmer()

    }

    private fun hideShimmerLoading() {
        mViewBinding.shimmerLayout.shimmerFrameLayout.gone()
        mViewBinding.shimmerLayout.shimmerFrameLayout.stopShimmer()
    }

    private fun viewModelObservers() {
        observe(viewModel.repoLists, ::handleRepoList)
        observe(viewModel.uiState, ::handleUiState)
    }
}