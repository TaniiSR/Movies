package com.task.movies.data.local.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.movies.data.dtos.responsedtos.UpcomingMovie

@Dao
interface MovieLocalDao {
    @Query("SELECT * FROM upcoming_movie")
    suspend fun getAllUpcomingMovies(): List<UpcomingMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcomingMovies(profiles: List<UpcomingMovie>)
}