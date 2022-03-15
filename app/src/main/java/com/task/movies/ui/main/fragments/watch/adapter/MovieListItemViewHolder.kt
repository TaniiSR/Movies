package com.task.movies.ui.main.fragments.watch.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.task.movies.BuildConfig
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.databinding.LayoutItemRepoListViewBinding
import com.task.movies.utils.base.interfaces.OnItemClickListener
import com.task.movies.utils.extensions.loadImage

class MovieListItemViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(
        data: UpcomingMovie,
        position: Int,
        onItemClickListener: OnItemClickListener?
    ) {
        when (binding) {
            is LayoutItemRepoListViewBinding -> {
                binding.ivPoster.loadImage(BuildConfig.IMAGE_BASE_URL + data.backdropPath)
                binding.tvMovieName.text = data.title
                binding.clMain.setOnClickListener {
                    onItemClickListener?.onItemClick(it, data, position)
                }
            }
        }
    }
}