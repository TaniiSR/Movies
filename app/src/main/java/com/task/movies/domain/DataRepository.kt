package com.task.movies.domain

import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.local.localservice.MovieRepoDbService
import com.task.movies.data.remote.baseclient.ApiResponse
import com.task.movies.data.remote.microservices.movierepos.MovieRepoApi
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: MovieRepoApi,
    private val localRepository: MovieRepoDbService
) : IDataRepository {
    override suspend fun getUpcomingMovies(
        apiKey: String,
        isRefresh: Boolean
    ): ApiResponse<UpcomingMoviesResponse> {
        val upcomingMovies = localRepository.getUpcomingMovies()
        return when {
            !isRefresh && upcomingMovies.isNotEmpty() -> {
                ApiResponse.Success(
                    200,
                    UpcomingMoviesResponse(results = upcomingMovies)
                )
            }
            else -> {
                val response = remoteRepository.getUpComingMovies(apiKey)
                if (response is ApiResponse.Success) {
                    response.data.results?.let { localRepository.insertUpcomingMovies(it) }
                }
                response
            }
        }
    }
}