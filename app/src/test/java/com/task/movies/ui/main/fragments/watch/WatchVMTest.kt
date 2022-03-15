package com.task.movies.ui.main.fragments.watch

import com.task.movies.base.BaseTestCase
import com.task.movies.base.getOrAwaitValue
import com.task.movies.data.dtos.responsedtos.UpcomingMoviesResponse
import com.task.movies.data.remote.baseclient.ApiResponse
import com.task.movies.domain.DataRepository
import com.task.movies.domain.IDataRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WatchVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: WatchVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk<DataRepository>()
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
            coEvery {
                dataRepo.getUpcomingMovies(
                    apiKey,
                    true
                )
            } returns response
            //2-Call
            viewModel = WatchVM(dataRepo)
            viewModel.getUpcomingMovies(apiKey, true)

            //3-verify
            Assert.assertEquals(false, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list with local data success`() {
        //1- Mock calls
        val apiKey = ""
        runTest {
            val response = mockk<ApiResponse.Success<UpcomingMoviesResponse>> {
                every { data } returns mockk {
                    every { results } returns listOf(mockk(), mockk())
                }
            }
            coEvery {
                dataRepo.getUpcomingMovies(
                    apiKey,
                    false
                )
            } returns response
            //2-Call
            viewModel = WatchVM(dataRepo)
            viewModel.getUpcomingMovies(apiKey, false)

            //3-verify
            Assert.assertEquals(false, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list error`() {
        //1- Mock calls
        val apiKey = ""
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getUpcomingMovies(
                    apiKey,
                    true
                )
            } returns response
            //2-Call
            viewModel = WatchVM(dataRepo)
            viewModel.getUpcomingMovies(apiKey, true)

            //3-verify
            Assert.assertEquals(true, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @Test
    fun `get trendy repo list with local data error`() {
        //1- Mock calls
        val apiKey = ""
        runTest {
            val response = mockk<ApiResponse.Error>()
            coEvery {
                dataRepo.getUpcomingMovies(
                    apiKey,
                    false
                )
            } returns response
            //2-Call
            viewModel = WatchVM(dataRepo)
            viewModel.getUpcomingMovies(apiKey, false)

            //3-verify
            Assert.assertEquals(true, viewModel.repoLists.getOrAwaitValue().isNullOrEmpty())
        }
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}