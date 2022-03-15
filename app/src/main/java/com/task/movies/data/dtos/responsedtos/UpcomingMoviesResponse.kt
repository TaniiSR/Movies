package com.task.movies.data.dtos.responsedtos


import com.google.gson.annotations.SerializedName
import com.task.movies.data.remote.baseclient.BaseApiResponse

data class UpcomingMoviesResponse(
    @SerializedName("dates")
    val dates: UpcomingMovieDate? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<UpcomingMovie>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : BaseApiResponse()