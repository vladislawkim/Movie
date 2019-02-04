package com.vladislawfox.movie.cinema.presentation.di

import com.vladislawfox.base.presentation.di.scope.PerScreen
import com.vladislawfox.movie.cinema.presentation.contract.MovieContract
import com.vladislawfox.movie.cinema.presentation.presenter.MoviePresenterImpl
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