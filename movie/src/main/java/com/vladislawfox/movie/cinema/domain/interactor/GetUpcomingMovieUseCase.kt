package com.vladislawfox.movie.cinema.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.interactor.UseCase
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.Page
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository) : UseCase<Page<Movie>, UpcomingMovieParams>() {
  override suspend fun run(params: UpcomingMovieParams): Either<Failure, Page<Movie>> =
      movieRepository.getUpcomingMovie(params.page, params.language)
}

class UpcomingMovieParams(val page: Int = 1, @Languages.Locale val language: String = Languages.US)