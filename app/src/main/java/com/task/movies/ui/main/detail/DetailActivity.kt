package com.task.movies.ui.main.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.task.movies.BuildConfig
import com.task.movies.R
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.databinding.ActivityDetailBinding
import com.task.movies.utils.Utils.getDateByMonthName
import com.task.movies.utils.base.BaseActivity
import com.task.movies.utils.extensions.loadImage
import com.task.movies.utils.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, IDetailVM>() {
    override val viewModel: IDetailVM by viewModels<DetailVM>()
    override fun getViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelObservers()
        viewModel.setUpcomingMovie(intent.getParcelableExtra(DetailActivity::class.java.name))
        mViewBinding.ivBack.setOnClickListener(this)
    }

    override fun onClick(id: Int) {
        when (id) {
            R.id.ivBack -> onBackPressed()
        }
    }

    private fun handleUpcomingMovie(data: UpcomingMovie) {
        mViewBinding.ivPoster.loadImage(BuildConfig.IMAGE_BASE_URL + data.posterPath)
        mViewBinding.tvRelease.text =
            getString(
                R.string.screen_dashboard_button_text_release,
                getDateByMonthName(data.releaseDate ?: "")
            )
    }

    private fun viewModelObservers() {
        observe(viewModel.upcomingMovie, ::handleUpcomingMovie)
    }
}