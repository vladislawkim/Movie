package com.vladislawfox.base.presentation.mvp

/**
 * Created by vladislawfox on 1/27/19.
 */
interface Presenter<V : MvpView> {
  fun attachView(view: V)
  fun detachView()
  fun onResume()
  fun onPause()
}