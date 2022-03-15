package com.task.movies.domain

import com.task.movies.base.BaseTestCase
import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.local.localservice.MovieRepoDbService
import com.task.movies.data.local.localservice.MovieRepositoryLocal
import com.task.movies.data.remote.baseclient.ApiResponse
import com.task.movies.data.remote.microservices.movierepos.MovieRepoApi
import com.task.movies.data.remote.microservices.movierepos.MovieRepositoryRemote
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest : BaseTestCase() {
    // Subject under test
    lateinit var dataRepository: DataRepository

    // Use a fake UseCase to be injected into the DataRepository
    lateinit var remoteData: MovieRepoApi
    lateinit var localData: MovieRepoDbService

    @Before
    fun setUp() {
        remoteData = mockk<MovieRepositoryRemote>()
        localData = mockk<MovieRepositoryLocal>()
    }

    @Test
    fun `get trendy repo list success`() {
        //1- Mock calls
        val apiKey = ""
        runTest {
            val response = mockk<ApiResponse.Success<UpcomingMoviesResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            val movies = response.data.results ?: listOf()

            coEvery {
                localData.insertUpcomingMovies(movies)
            } returns Unit

            coEvery {
                localData.getUpcomingMovies()
            } returns movies

            coEvery {
                remoteData.getUpComingMovies(
                    apiKey
                )
            } returns response
            //2-Call
            dataRepository = DataRepository(remoteData, localData)
            dataRepository.getUpcomingMovies(apiKey, true)

            //3-verify
            coVerify { dataRepository.getUpcomingMovies(apiKey, true) }

        }
    }

    @Test
    fun `get movie list with local success`() {
        //1- Mock calls
        val query = ""
        runTest {
            val response = mockk<ApiResponse.Success<UpcomingMoviesResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            val profiles = response.data.results ?: listOf()

            coEvery {
                localData.getUpcomingMovies()
            } returns profiles

            //2-Call
            dataRepository = DataRepository(remoteData, localData)
            val mockedCallResponse = dataRepository.getUpcomingMovies(query, false)

            //3-verify
            when (mockedCallResponse) {
                is ApiResponse.Success -> {
                    Assert.assertEquals(profiles, mockedCallResponse.data.results)
                }
            }
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}