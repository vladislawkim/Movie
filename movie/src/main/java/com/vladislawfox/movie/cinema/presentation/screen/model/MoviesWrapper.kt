package com.vladislawfox.movie.cinema.presentation.screen.model

import com.vladislawfox.movie.cinema.domain.Movie

/**
 * Created by vladislawfox on 1/30/19.
 */
class MoviesWrapper(
    val popularMovie: List<Movie>,
    val topMovie: List<String> = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
    val nowMovie: List<String> = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
    val upcomingMovie: List<String> = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))