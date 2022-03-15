package com.task.movies.di

import com.task.movies.data.remote.baseclient.RetroNetwork
import com.task.movies.data.remote.microservices.movierepos.MovieRepoRetroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesGitRepoRetroService(): MovieRepoRetroService =
        RetroNetwork().createService(MovieRepoRetroService::class.java)
}