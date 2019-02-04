package com.vladislawfox.movie.cinema.presentation.presenter

import com.vladislawfox.base.presentation.mvp.BasePresenter
import com.vladislawfox.base.presentation.utils.DevUtils
import com.vladislawfox.movie.cinema.domain.GetPopularMovieUseCase
import com.vladislawfox.movie.cinema.domain.PopularMovieParams
import com.vladislawfox.movie.cinema.presentation.contract.MovieContract
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/27/19.
 */
class MoviePresenterImpl @Inject constructor(private val getPopularMovieUseCase: GetPopularMovieUseCase) :
    BasePresenter<MovieContract.View>(), MovieContract.Presenter {

    override fun requestData() {
        loadPopularMovie()
    }

    private fun loadPopularMovie() {
        getPopularMovieUseCase.invoke(PopularMovieParams(), onResult = {
            it.either(
                fnL = {
                    DevUtils.log("Fail")
                }, fnR = {
                    ifViewAttached { view -> view.showData(it.items) }
                })
        })
    }
}