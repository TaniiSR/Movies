package com.task.movies.ui.main.fragments.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.movies.BuildConfig
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.data.remote.baseclient.ApiResponse
import com.task.movies.domain.IDataRepository
import com.task.movies.ui.main.fragments.watch.adapter.MovieListAdapter
import com.task.movies.utils.base.BaseViewModel
import com.task.movies.utils.base.sealed.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WatchVM @Inject constructor(
    private val dataRepository: IDataRepository
) : BaseViewModel(), IWatch {
    private val _uiState: MutableLiveData<UIEvent> = MutableLiveData()
    override val uiState: LiveData<UIEvent> = _uiState

    private val _repoLists: MutableLiveData<List<UpcomingMovie>> = MutableLiveData()
    override val repoLists: LiveData<List<UpcomingMovie>> = _repoLists

    override val apiKey: String = BuildConfig.API_KEY
    override val adaptor: MovieListAdapter = MovieListAdapter(mutableListOf())

    override fun fetchFreshData() {
        getUpcomingMovies(apiKey = apiKey, true)
    }

    override fun getUpcomingMovies(apiKey: String, isRefresh: Boolean) {
        launch {
            _uiState.postValue(UIEvent.Loading)
            val response = dataRepository.getUpcomingMovies(apiKey, isRefresh)
            withContext(Dispatchers.Main) {
                when (response) {
                    is ApiResponse.Success -> {
                        _repoLists.value = response.data.results ?: listOf()
                        _uiState.value = UIEvent.Success
                    }
                    is ApiResponse.Error -> {
                        _repoLists.value = listOf()
                        _uiState.value = UIEvent.Error(response.error.message)
                    }
                }
            }
        }
    }
}