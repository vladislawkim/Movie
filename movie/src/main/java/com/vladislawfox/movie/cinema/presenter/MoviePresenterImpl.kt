package com.vladislawfox.movie.cinema.presenter

import com.vladislawfox.base.presentation.mvp.BasePresenter
import com.vladislawfox.movie.cinema.contract.MovieContract
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/27/19.
 */
class MoviePresenterImpl @Inject constructor() : BasePresenter<MovieContract.View>(), MovieContract.Presenter