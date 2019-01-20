package com.vladislawfox.auth.presentation.di

import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.base.presentation.platform.NetworkHandler
import retrofit2.Retrofit

/**
 * Created by vladislawfox on 1/20/19.
 */
interface AuthComponentApi {
    fun retrofit(): Retrofit
    fun networkHandler(): NetworkHandler
    fun authRepository(): AuthRepository
}