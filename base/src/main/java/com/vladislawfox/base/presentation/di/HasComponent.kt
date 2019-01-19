package com.vladislawfox.base.presentation.di

/**
 * Created by vladislawfox on 1/19/19.
 */
interface HasComponent<C> {
    fun initializeInjector()
    fun getComponent(): C
}