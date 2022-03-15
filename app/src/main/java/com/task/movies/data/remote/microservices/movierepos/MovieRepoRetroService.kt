package com.task.movies.data.remote.microservices.movierepos

import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepoRetroService {
    @GET(MovieRepositoryRemote.URL_GET_UPCOMING_MOVIES)
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String
    ): Response<UpcomingMoviesResponse>
}
