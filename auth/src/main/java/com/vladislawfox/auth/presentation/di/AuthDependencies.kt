package com.vladislawfox.auth.presentation.di

import android.content.Context
import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.base.data.storage.PreferenceUtils
import com.vladislawfox.base.presentation.platform.NetworkHandler
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/20/19.
 */
interface AuthDependencies {
    fun retrofit(): Retrofit
    fun context(): Context
    fun networkHandler(): NetworkHandler
    fun preferenceUtils(): PreferenceUtils
}