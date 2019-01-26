package com.vladislawfox.base.presentation.di.component

import android.content.Context
import com.vladislawfox.base.data.storage.PreferenceUtils
import com.vladislawfox.base.presentation.platform.NetworkHandler
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/19/19.
 */
interface BaseAppComponentApi {
    fun retrofit(): Retrofit
    fun context(): Context
    fun preferenceUtils(): PreferenceUtils
    fun okHttpClient(): OkHttpClient
    fun networkHandler(): NetworkHandler
}