package com.vladislawfox.movie.cinema.data.repository

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.functional.map
import com.vladislawfox.movie.cinema.data.mapper.MovieEntityMapper
import com.vladislawfox.movie.cinema.data.model.MovieEntity
import com.vladislawfox.movie.cinema.data.repository.source.MovieDataSourceProvider
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.domain.repository.MovieRepository
import com.vladislawfox.movie.cinema.domain.Page
import javax.inject.Inject

/**
 * Created by vladislawfox on 2/4/19.
 */
class MovieRepositoryImpl @Inject constructor(
    private val dataSourceProvider: MovieDataSourceProvider,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {

  override fun getPopularMovie(page: Int, language: String): Either<Failure, Page<Movie>> =
      dataSourceProvider.remoteDataSource.getPopularMovie(page, language).map {
        mapPageMovieEntity(it)
      }

  override fun getTopMovie(page: Int, language: String): Either<Failure, Page<Movie>> =
      dataSourceProvider.remoteDataSource.getTopMovie(page, language).map { mapPageMovieEntity(it) }

  override fun getNowPlayingMovie(page: Int, language: String): Either<Failure, Page<Movie>> =
      dataSourceProvider.remoteDataSource.getNowPlayingMovie(page,
          language).map { mapPageMovieEntity(it) }

  override fun getUpcomingMovie(page: Int, language: String): Either<Failure, Page<Movie>> =
      dataSourceProvider.remoteDataSource.getUpcomingMovie(page, language).map {
        mapPageMovieEntity(it)
      }

  private fun mapPageMovieEntity(page: Page<MovieEntity>) = page.run {
    Page(currentPage, totalPages, totalItems, movieEntityMapper.map(items))
  }
}