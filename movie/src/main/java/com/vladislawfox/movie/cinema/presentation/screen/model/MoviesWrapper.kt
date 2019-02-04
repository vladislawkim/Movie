package com.vladislawfox.movie.cinema.presentation.screen.model

import com.vladislawfox.movie.cinema.domain.model.Movie

/**
 * Created by vladislawfox on 1/30/19.
 */
class MoviesWrapper(
    val popularMovie: List<Movie>? = emptyList(),
    val topMovie: List<Movie>? = emptyList(),
    val nowMovie: List<Movie>? = emptyList(),
    val upcomingMovie: List<Movie>? = emptyList())