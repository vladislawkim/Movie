package com.vladislawfox.base

import android.app.Application
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.di.component.BaseAppComponent
import com.vladislawfox.base.presentation.di.component.DaggerBaseAppComponent
import com.vladislawfox.base.presentation.di.module.ContextModule

/**
 * Created by vladislawfox on 1/20/19.
 */
class MovieApp : Application(), HasComponent<BaseAppComponent> {

    private lateinit var appComponent: BaseAppComponent

    init {
        initializeInjector()
        getComponent().inject(this)
    }

    override fun initializeInjector() {
        this.appComponent = DaggerBaseAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
        BaseAppComponent.init(appComponent)
    }

    override fun getComponent(): BaseAppComponent = appComponent
}