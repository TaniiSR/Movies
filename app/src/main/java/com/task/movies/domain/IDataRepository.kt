package com.task.movies.domain

import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.remote.baseclient.ApiResponse

interface IDataRepository {
    suspend fun getUpcomingMovies(
        query: String,
        isRefresh: Boolean
    ): ApiResponse<UpcomingMoviesResponse>
}