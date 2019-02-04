package com.vladislawfox.movie.di.feature

import com.vladislawfox.base.data.storage.PreferenceUtils
import com.vladislawfox.base.presentation.platform.NetworkHandler
import com.vladislawfox.movie.cinema.domain.MovieRepository
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/27/19.
 */
interface DashboardComponentApi {
    fun movieRepository(): MovieRepository
    fun retrofit(): Retrofit
    fun networkHandler(): NetworkHandler
    fun preferenceUtils(): PreferenceUtils
}