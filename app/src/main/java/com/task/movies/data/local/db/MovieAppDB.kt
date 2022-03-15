package com.task.movies.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.movies.data.dtos.responsedtos.UpcomingMovie
import com.task.movies.data.local.converter.DataConverter
import com.task.movies.data.local.localservice.MovieLocalDao

@Database(entities = [UpcomingMovie::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieAppDB : RoomDatabase() {
    abstract fun MovieLocalDao(): MovieLocalDao
}