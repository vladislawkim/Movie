package com.vladislawfox.movie.cinema.data.repository

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import com.vladislawfox.base.domain.functional.map
import com.vladislawfox.movie.cinema.data.mapper.MovieEntityMapper
import com.vladislawfox.movie.cinema.data.repository.source.MovieDataSourceProvider
import com.vladislawfox.movie.cinema.domain.Movie
import com.vladislawfox.movie.cinema.domain.MovieRepository
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
            Page(
                it.currentPage,
                it.totalPages,
                it.totalItems,
                movieEntityMapper.map(it.items)
            )
        }
}