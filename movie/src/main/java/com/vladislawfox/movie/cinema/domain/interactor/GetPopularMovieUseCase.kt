package com.vladislawfox.movie.cinema.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.interactor.UseCase
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.Page
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by vladislawfox on 2/4/19.
 */
class GetPopularMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<Page<Movie>, PopularMovieParams>() {
    override suspend fun run(params: PopularMovieParams): Either<Failure, Page<Movie>> =
        movieRepository.getPopularMovie(params.page, params.language)
}

class PopularMovieParams(val page: Int = 1, @Languages.Locale val language: String = Languages.US)