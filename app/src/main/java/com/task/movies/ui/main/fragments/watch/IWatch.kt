package com.task.movies.ui.main.fragments.watch

import androidx.lifecycle.LiveData
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.ui.main.fragments.watch.adapter.MovieListAdapter
import com.task.movies.utils.base.interfaces.IBaseViewModel
import com.task.movies.utils.base.sealed.UIEvent

interface IWatch : IBaseViewModel {
    val uiState: LiveData<UIEvent>
    val repoLists: LiveData<List<UpcomingMovie>>
    val apiKey: String
    val adaptor: MovieListAdapter
    fun fetchFreshData()
    fun getUpcomingMovies(apiKey: String, isRefresh: Boolean)
}