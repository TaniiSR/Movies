package com.task.movies.ui.main.fragments.more

import androidx.fragment.app.viewModels
import com.task.movies.R
import com.task.movies.databinding.FragmentMoreBinding
import com.task.movies.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment :
    BaseFragment<FragmentMoreBinding, IMore>(R.layout.fragment_more) {
    override val viewModel: IMore by viewModels<MoreVM>()
    override fun getViewBinding(): FragmentMoreBinding =
        FragmentMoreBinding.inflate(layoutInflater)

    override fun onClick(id: Int) {
        //
    }
}