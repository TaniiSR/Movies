package com.task.movies.data.remote.microservices.movierepos

import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.remote.baseclient.ApiResponse


interface MovieRepoApi {
    suspend fun getUpComingMovies(apiKey: String): ApiResponse<UpcomingMoviesResponse>
}