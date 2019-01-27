package com.vladislawfox.base.presentation.mvp

import androidx.annotation.CallSuper
import com.vladislawfox.base.presentation.functional.CompositeJob

/**
 * Created by vladislawfox on 1/27/19.
 */
abstract class BasePresenter<V : MvpView> : Presenter<V> {

  protected val jobs = CompositeJob()
  protected var view: V? = null

  override fun onResume() {
  }

  override fun onPause() {
    jobs.cancel()
  }

  @CallSuper
  override fun attachView(view: V) {
    this.view = view
  }

  @CallSuper
  override fun detachView() {
    this.view = null
  }
}