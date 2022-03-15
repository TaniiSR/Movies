package com.task.movies.di

import com.task.movies.data.local.localservice.MovieRepoDbService
import com.task.movies.data.local.localservice.MovieRepositoryLocal
import com.task.movies.data.remote.microservices.movierepos.MovieRepoApi
import com.task.movies.data.remote.microservices.movierepos.MovieRepositoryRemote
import com.task.movies.domain.DataRepository
import com.task.movies.domain.IDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideMovieRemoteRepository(movieRepositoryRemote: MovieRepositoryRemote): MovieRepoApi

    @Binds
    @Singleton
    abstract fun provideMovieLocalRepository(movieRepositoryLocal: MovieRepositoryLocal): MovieRepoDbService

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): IDataRepository
}