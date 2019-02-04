package com.vladislawfox.movie.cinema.data.repository.source

import com.vladislawfox.base.data.store.BaseRemoteDataStore
import com.vladislawfox.base.domain.functional.map
import com.vladislawfox.movie.cinema.data.net.MovieService
import com.vladislawfox.movie.cinema.data.net.response.MoviesResponse
import com.vladislawfox.movie.cinema.domain.Page
import javax.inject.Inject

/**
 * Created by vladislawfox on 2/4/19.
 */
class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) : BaseRemoteDataStore(),
    MovieDataSource {
    override fun getPopularMovie(page: Int, language: String) =
        request(movieService.getPopularMovie(page, language), MoviesResponse())
            .map { Page(it.page, it.totalPages, it.totalPages, it.results) }

}