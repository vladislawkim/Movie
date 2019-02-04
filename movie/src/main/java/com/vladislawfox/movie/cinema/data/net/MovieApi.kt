package com.vladislawfox.movie.cinema.data.net

import com.vladislawfox.movie.cinema.data.net.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vladislawfox on 2/4/19.
 */

const val POPULAR_MOVIE_ENDPOINT = "/3/movie/popular"
const val TOP_MOVIE_ENDPOINT = "/3/movie/top_rated"
const val NOW_MOVIE_ENDPOINT = "/3/movie/now_playing"
const val UPCOMING_MOVIE_ENDPOINT = "/3/movie/upcoming"

interface MovieApi {
  @GET(POPULAR_MOVIE_ENDPOINT)
  fun getPopularMovie(@Query("page") page: Int, @Query(
      "language") language: String): Call<MoviesResponse>

  @GET(TOP_MOVIE_ENDPOINT)
  fun getTopMovie(@Query("page") page: Int, @Query(
      "language") language: String): Call<MoviesResponse>

  @GET(NOW_MOVIE_ENDPOINT)
  fun getNowPlayingMovie(@Query("page") page: Int, @Query(
      "language") language: String): Call<MoviesResponse>

  @GET(UPCOMING_MOVIE_ENDPOINT)
  fun getUpcomingMovie(@Query("page") page: Int, @Query(
      "language") language: String): Call<MoviesResponse>
}