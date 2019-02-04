package com.vladislawfox.movie.di.feature

import com.vladislawfox.base.presentation.di.scope.PerFeature
import com.vladislawfox.movie.cinema.data.repository.MovieRepositoryImpl
import com.vladislawfox.movie.cinema.domain.MovieRepository
import dagger.Binds
import dagger.Module

/**
 * Created by vladislawfox on 2/4/19.
 */
@Module
abstract class DashboardDataModule {
    @Binds
    @PerFeature
    abstract fun bindRepository(impl: MovieRepositoryImpl): MovieRepository
}