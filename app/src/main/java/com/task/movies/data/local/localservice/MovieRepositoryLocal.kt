package com.task.movies.data.local.localservice

import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import javax.inject.Inject

class MovieRepositoryLocal @Inject constructor(private val movieLocalDao: MovieLocalDao) :
    MovieRepoDbService {
    override suspend fun getUpcomingMovies() = movieLocalDao.getAllUpcomingMovies()

    override suspend fun insertUpcomingMovies(upcomingMovies: List<UpcomingMovie>) =
        movieLocalDao.insertAllUpcomingMovies(profiles = upcomingMovies)
}