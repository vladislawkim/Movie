package com.vladislawfox.movie.cinema.di

import com.vladislawfox.base.presentation.di.scope.PerScreen
import com.vladislawfox.movie.cinema.MovieContract
import com.vladislawfox.movie.cinema.MoviePresenterImpl
import dagger.Binds
import dagger.Module

/**
 * Created by vladislawfox on 1/30/19.
 */
@Module
abstract class MovieModule {

    @PerScreen
    @Binds
    abstract fun bindMoviePresenter(presenter: MoviePresenterImpl): MovieContract.Presenter
}