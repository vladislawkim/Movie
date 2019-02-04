package com.vladislawfox.movie.cinema.data.net

import com.vladislawfox.movie.cinema.data.net.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vladislawfox on 2/4/19.
 */

const val POPULAR_MOVIE_ENDPOINT = "/3/movie/popular"

interface MovieApi {
    @GET(POPULAR_MOVIE_ENDPOINT)
    fun getPopularMovie(@Query("page") page: Int, @Query("language") language: String): Call<MoviesResponse>
}