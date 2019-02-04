package com.vladislawfox.movie.cinema.domain

/**
 * Created by vladislawfox on 2/4/19.
 */
data class Movie(
    val overview: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val video: Boolean = false,
    val title: String = "",
    val genreIds: List<Int> = emptyList(),
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val id: Int = 0,
    val adult: Boolean = false,
    val voteCount: Int = 0
)