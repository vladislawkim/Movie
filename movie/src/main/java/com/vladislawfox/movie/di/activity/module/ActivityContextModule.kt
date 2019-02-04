package com.vladislawfox.movie.di.activity.module

import android.app.Activity
import android.content.Context
import com.vladislawfox.base.presentation.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by vladislawfox on 1/27/19.
 */
@Module
class ActivityContextModule(private val activity: Activity) {
    @Provides
    @PerActivity
    fun provideActivity(): Context {
        return activity
    }
}