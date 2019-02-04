package com.vladislawfox.movie.cinema.data.repository.source

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.movie.cinema.data.model.MovieEntity
import com.vladislawfox.movie.cinema.data.net.response.MoviesResponse
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.Page


/**
 * Created by vladislawfox on 2/4/19.
 */
interface MovieDataSource {
    fun getPopularMovie(page: Int, @Languages.Locale language: String): Either<Failure, Page<MovieEntity>>
}