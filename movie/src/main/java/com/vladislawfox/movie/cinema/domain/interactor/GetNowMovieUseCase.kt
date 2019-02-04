package com.vladislawfox.movie.cinema.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.interactor.UseCase
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.Page
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository) : UseCase<Page<Movie>, NowMovieParams>() {
  override suspend fun run(params: NowMovieParams): Either<Failure, Page<Movie>> =
      movieRepository.getNowPlayingMovie(params.page, params.language)
}

class NowMovieParams(val page: Int = 1, @Languages.Locale val language: String = Languages.US)