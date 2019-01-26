package com.vladislawfox.auth.presentation.di

import com.vladislawfox.auth.data.repository.AuthRepositoryImpl
import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.base.presentation.di.scope.PerFeature
import dagger.Binds
import dagger.Module

/**
 * Created by vladislawfox on 1/20/19.
 */
@Module
abstract class AuthDataModule {
    @PerFeature
    @Binds
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}