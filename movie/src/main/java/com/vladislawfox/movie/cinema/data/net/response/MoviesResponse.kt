package com.vladislawfox.movie.cinema.data.net.response

import com.google.gson.annotations.SerializedName
import com.vladislawfox.movie.cinema.data.model.MovieEntity

/**
 * Created by vladislawfox on 2/4/19.
 */
class MoviesResponse(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("total_results") val totalResults: Int = 0,
    @SerializedName("total_pages") val totalPages: Int = 0,
    @SerializedName("results") val results: List<MovieEntity> = emptyList()
)