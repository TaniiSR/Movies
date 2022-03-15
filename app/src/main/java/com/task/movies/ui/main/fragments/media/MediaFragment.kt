package com.task.movies.ui.main.fragments.media

import androidx.fragment.app.viewModels
import com.task.movies.R
import com.task.movies.databinding.FragmentMediaBinding
import com.task.movies.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaFragment :
    BaseFragment<FragmentMediaBinding, IMedia>(R.layout.fragment_media) {
    override val viewModel: IMedia by viewModels<MediaVM>()
    override fun getViewBinding(): FragmentMediaBinding =
        FragmentMediaBinding.inflate(layoutInflater)

    override fun onClick(id: Int) {
        //
    }
}