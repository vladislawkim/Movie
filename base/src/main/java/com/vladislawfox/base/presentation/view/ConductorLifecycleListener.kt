package com.vladislawfox.base.presentation.view

import android.view.View
import com.bluelinelabs.conductor.Controller

/**
 * Created by vladislawfox on 1/19/19.
 */
class ConductorLifecycleListener : Controller.LifecycleListener() {
    override fun postCreateView(controller: Controller, view: View) {
        super.postCreateView(controller, view)
        (controller as BaseViewController<*, *, *>).createPresenter()
    }
}