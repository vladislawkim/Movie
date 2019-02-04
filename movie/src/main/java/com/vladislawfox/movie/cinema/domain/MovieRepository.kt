package com.vladislawfox.movie.cinema.domain

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either

/**
 * Created by vladislawfox on 2/4/19.
 */
interface MovieRepository {
    fun getPopularMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<Movie>>
}