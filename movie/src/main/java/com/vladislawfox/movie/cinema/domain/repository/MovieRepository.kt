package com.vladislawfox.movie.cinema.domain.repository

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.Page

/**
 * Created by vladislawfox on 2/4/19.
 */
interface MovieRepository {
    fun getPopularMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<Movie>>
    fun getTopMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<Movie>>
    fun getNowPlayingMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<Movie>>
    fun getUpcomingMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<Movie>>
}