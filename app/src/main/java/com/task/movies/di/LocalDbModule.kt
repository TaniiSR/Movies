package com.task.movies.di

import android.content.Context
import androidx.room.Room
import com.task.movies.data.local.db.MovieAppDB
import com.task.movies.data.local.localservice.MovieLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {
    @Provides
    fun provideTrendyDao(appDatabase: MovieAppDB): MovieLocalDao {
        return appDatabase.MovieLocalDao()
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): MovieAppDB {
        return Room.databaseBuilder(
            appContext,
            MovieAppDB::class.java,
            "MovieAppDB"
        ).build()
    }

}