package com.task.movies.ui.main.fragments.watch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.utils.base.BaseRecyclerAdapter
import com.task.movies.R
import com.task.movies.databinding.LayoutItemRepoListViewBinding

class MovieListAdapter(
    private val list: MutableList<UpcomingMovie>,
) : BaseRecyclerAdapter<UpcomingMovie, MovieListItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewBinding): MovieListItemViewHolder {
        return MovieListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_item_repo_list_view
    }

    override fun getItemCount(): Int = list.size

    override fun getViewBindingByViewType(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewBinding {
        return LayoutItemRepoListViewBinding.inflate(layoutInflater, viewGroup, false)
    }
}