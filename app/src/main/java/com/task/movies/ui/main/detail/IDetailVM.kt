package com.task.movies.ui.main.detail

import androidx.lifecycle.LiveData
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.utils.base.interfaces.IBaseViewModel

interface IDetailVM : IBaseViewModel {
    val upcomingMovie: LiveData<UpcomingMovie>
    fun setUpcomingMovie(movie: UpcomingMovie?)
}