package com.vladislawfox.movie.cinema.presentation.presenter

import com.vladislawfox.base.presentation.mvp.BasePresenter
import com.vladislawfox.base.presentation.utils.DevUtils
import com.vladislawfox.movie.cinema.domain.interactor.GetMoviesUseCase
import com.vladislawfox.movie.cinema.domain.interactor.GetPopularMovieUseCase
import com.vladislawfox.movie.cinema.domain.interactor.PopularMovieParams
import com.vladislawfox.movie.cinema.presentation.contract.MovieContract
import com.vladislawfox.movie.cinema.presentation.screen.model.MoviesWrapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/27/19.
 */
class MoviePresenterImpl @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getMoviesUseCase: GetMoviesUseCase) :
    BasePresenter<MovieContract.View>(), MovieContract.Presenter {

    override fun requestData() {
        getMoviesUseCase.invoke(onResult = {
            it.either(fnL = {
                DevUtils.log("Fail")
            }, fnR = {
                ifViewAttached { view -> view.showData(MoviesWrapper(it.popular?.items, it.top?.items, it.now?.items, it.upcoming?.items)) }
            })
        })
//        loadPopularMovie()
    }

    private fun loadPopularMovie() {
        getPopularMovieUseCase.invoke(
            PopularMovieParams(), onResult = {
            it.either(
                fnL = {
                    DevUtils.log("Fail")
                }, fnR = {
                    ifViewAttached { view -> view.showData(it.items) }
                })
        })
    }
}