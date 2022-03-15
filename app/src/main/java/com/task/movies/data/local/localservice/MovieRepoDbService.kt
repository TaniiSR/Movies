package com.task.movies.data.local.localservice

import com.task.movies.data.dtos.responsedtos.UpcomingMovie

interface MovieRepoDbService {
    suspend fun getUpcomingMovies(): List<UpcomingMovie>
    suspend fun insertUpcomingMovies(upcomingMovies: List<UpcomingMovie>)
}