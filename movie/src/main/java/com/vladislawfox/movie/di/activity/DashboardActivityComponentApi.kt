package com.vladislawfox.movie.di.activity

import android.content.Context
import com.vladislawfox.movie.cinema.domain.MovieRepository

/**
 * Created by vladislawfox on 1/27/19.
 */
interface DashboardActivityComponentApi {
    fun context(): Context
    fun movieRepository(): MovieRepository
}