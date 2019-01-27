package com.vladislawfox.movie.dashboard

import android.content.Context
import com.vladislawfox.base.data.storage.PreferenceUtils
import com.vladislawfox.base.presentation.platform.NetworkHandler
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/27/19.
 */
interface DashboardDependencies {
  fun retrofit(): Retrofit
  fun context(): Context
  fun networkHandler(): NetworkHandler
  fun preferenceUtils(): PreferenceUtils
}