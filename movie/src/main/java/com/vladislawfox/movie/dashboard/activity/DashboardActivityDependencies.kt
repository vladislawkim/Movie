package com.vladislawfox.movie.dashboard.activity

import com.vladislawfox.base.data.storage.PreferenceUtils
import com.vladislawfox.base.presentation.platform.NetworkHandler
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/27/19.
 */
interface DashboardActivityDependencies {
    fun retrofit(): Retrofit
    fun networkHandler(): NetworkHandler
    fun preferenceUtils(): PreferenceUtils
}