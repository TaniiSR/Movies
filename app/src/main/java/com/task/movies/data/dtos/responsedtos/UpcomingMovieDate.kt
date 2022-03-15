package com.task.movies.data.dtos.responsedtos


import com.google.gson.annotations.SerializedName


data class UpcomingMovieDate(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?
)