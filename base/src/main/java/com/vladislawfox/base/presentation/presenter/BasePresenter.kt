package com.vladislawfox.base.presentation.presenter

import android.os.Bundle
import androidx.annotation.CallSuper
import com.vladislawfox.base.presentation.functional.CompositeJob
import com.vladislawfox.base.presentation.view.MvpView

/**
 * Created by vladislawfox on 1/19/19.
 */
open class BasePresenter<V : MvpView> : Presenter<V> {
    protected val jobs = CompositeJob()
    protected var view: V? = null

    override fun setArgs(bundle: Bundle) {
    }

    @CallSuper
    override fun createView() {
    }

    @CallSuper
    override fun attachView(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detachView() {
        jobs.cancel()
        this.view = null
    }

    @CallSuper
    override fun destroyView() {
    }
}