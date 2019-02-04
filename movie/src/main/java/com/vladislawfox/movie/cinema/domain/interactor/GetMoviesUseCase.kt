package com.vladislawfox.movie.cinema.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.functional.flatMap
import com.vladislawfox.base.domain.functional.map
import com.vladislawfox.base.domain.interactor.UseCaseWithoutParams
import com.vladislawfox.movie.cinema.domain.Languages
import com.vladislawfox.movie.cinema.domain.MovieWrapper
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository) : UseCaseWithoutParams<MovieWrapper>() {
  override suspend fun run(): Either<Failure, MovieWrapper> {
    val result = MovieWrapper()
    return movieRepository.getTopMovie(1, Languages.US)
        .flatMap {
          result.top = it
          movieRepository.getPopularMovie(1, Languages.US)
        }.flatMap {
          result.popular = it
          movieRepository.getNowPlayingMovie(1, Languages.US)
        }.flatMap {
          result.now = it
          movieRepository.getUpcomingMovie(1, Languages.US)
        }.map {
          result.apply { upcoming = it }
        }
  }
}
