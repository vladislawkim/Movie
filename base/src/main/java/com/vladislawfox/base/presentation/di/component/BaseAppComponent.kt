package com.vladislawfox.base.presentation.di.component

import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.api.BaseAppComponentApi
import com.vladislawfox.base.presentation.di.module.ContextModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vladislawfox on 1/19/19.
 */
@Singleton
@Component(modules = [ContextModule::class])
interface BaseAppComponent : BaseComponent, BaseAppComponentApi {
    companion object {
        @Volatile
        private lateinit var baseAppComponent: BaseAppComponent

        fun init(baseAppComponent: BaseAppComponent) {
            if (this::baseAppComponent.isInitialized) {
                throw IllegalArgumentException("BaseAppComponent is already initialized.")
            }
            this.baseAppComponent = baseAppComponent
        }

        fun get(): BaseAppComponent {
            if (!this::baseAppComponent.isInitialized) {
                throw NullPointerException("BaseAppComponent is not initialized yet. Call init first.")
            }
            return baseAppComponent
        }
    }
}