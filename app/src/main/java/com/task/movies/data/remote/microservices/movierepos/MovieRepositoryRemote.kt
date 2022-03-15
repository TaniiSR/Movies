package com.task.movies.data.remote.microservices.movierepos

import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.remote.baseclient.ApiResponse
import com.task.movies.data.remote.baseclient.BaseRepository
import javax.inject.Inject

class MovieRepositoryRemote @Inject constructor(
    private val service: MovieRepoRetroService
) : BaseRepository(), MovieRepoApi {

    companion object {
        const val URL_GET_UPCOMING_MOVIES = "movie/upcoming"
    }

    override suspend fun getUpComingMovies(apiKey: String): ApiResponse<UpcomingMoviesResponse> {
        return executeSafely(call = {
            service.getUpComingMovies(apiKey = apiKey)
        })
    }
}