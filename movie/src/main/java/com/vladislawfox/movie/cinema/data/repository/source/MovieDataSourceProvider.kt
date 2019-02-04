package com.vladislawfox.movie.cinema.data.repository.source

import javax.inject.Inject

/**
 * Created by vladislawfox on 2/4/19.
 */
class MovieDataSourceProvider @Inject constructor(val remoteDataSource: MovieRemoteDataSource)