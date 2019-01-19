package com.vladislawfox.base.presentation.presenter

import android.os.Bundle
import androidx.annotation.NonNull
import com.vladislawfox.base.presentation.view.MvpView

/**
 * Created by vladislawfox on 1/19/19.
 */
interface Presenter<V : MvpView> {
    fun setArgs(bundle: Bundle)

    fun createView()

    /**
     * Set or attach the view to this presenter
     */
    fun attachView(@NonNull view: V)

    /**
     * Will be called if the view has been detached from the Presenter.
     * Usually this happens on screen orientation changes or view (like fragment) has been put on the backstack.
     */
    fun detachView()

    /**
     * Will be called if the presenter is no longer needed because the View has been destroyed permanently.
     * This is where you do clean up stuff.
     */
    fun destroyView()
}