package com.vladislawfox.base.presentation.mvp

import androidx.annotation.CallSuper
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.vladislawfox.base.presentation.functional.CompositeJob

/**
 * Created by vladislawfox on 1/27/19.
 */
abstract class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {

    protected val jobs = CompositeJob()

    @CallSuper
    override fun attachView(view: V) {
        super.attachView(view)
    }

    @CallSuper
    override fun detachView() {
        jobs.cancel()
        super.detachView()
    }

    @CallSuper
    override fun destroy() {
        super.destroy()
    }
}