package com.vladislawfox.movie.cinema

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by vladislawfox on 1/27/19.
 */
interface MovieContract {
  interface Presenter: MvpPresenter<View>
  interface View : MvpView
}