package com.task.movies.ui.main.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.domain.IDataRepository
import com.task.movies.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(private val dataRepository: IDataRepository) : BaseViewModel(),
    IDetailVM {

    private val _upcomingMovie: MutableLiveData<UpcomingMovie> = MutableLiveData()
    override val upcomingMovie: LiveData<UpcomingMovie> = _upcomingMovie

    override fun setUpcomingMovie(movie: UpcomingMovie?) {
        movie?.let {
            _upcomingMovie.value = it
        }
    }

}