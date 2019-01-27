package com.vladislawfox.movie.cinema

import com.vladislawfox.base.presentation.mvp.MvpView
import com.vladislawfox.base.presentation.mvp.Presenter

/**
 * Created by vladislawfox on 1/27/19.
 */
interface MovieContract {
  interface Presenter: com.vladislawfox.base.presentation.mvp.Presenter<View>
  interface View : MvpView
}