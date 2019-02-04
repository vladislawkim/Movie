package com.vladislawfox.movie.cinema.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.interactor.UseCase
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.Page
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import javax.inject.Inject

class GetTopMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository) : UseCase<Page<Movie>, TopMovieParams>() {
  override suspend fun run(params: TopMovieParams): Either<Failure, Page<Movie>> =
      movieRepository.getTopMovie(params.page, params.language)
}

class TopMovieParams(val page: Int = 1, @Languages.Locale val language: String = Languages.US)