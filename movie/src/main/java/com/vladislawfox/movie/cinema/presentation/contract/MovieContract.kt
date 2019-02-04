package com.vladislawfox.movie.cinema.presentation.contract

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vladislawfox.movie.cinema.domain.Movie

/**
 * Created by vladislawfox on 1/27/19.
 */
interface MovieContract {
  interface Presenter: MvpPresenter<View> {
    fun requestData()
  }
  interface View : MvpView {
    fun showData(list: List<Movie>)
  }

}