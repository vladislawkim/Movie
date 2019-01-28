package com.vladislawfox.movie.dashboard.activity

import com.vladislawfox.base.presentation.di.scope.PerActivity
import com.vladislawfox.movie.MainActivity
import com.vladislawfox.movie.dashboard.DashboardComponentApi
import com.vladislawfox.movie.dashboard.activity.module.ActivityContextModule
import dagger.Component

/**
 * Created by vladislawfox on 1/27/19.
 */
@PerActivity
@Component(
    dependencies = [DashboardActivityDependencies::class],
    modules = [ActivityContextModule::class]
)
interface DashboardActivityComponent : DashboardActivityComponentApi {

    fun inject(activity: MainActivity)

    @Component(dependencies = [DashboardComponentApi::class])
    @PerActivity
    interface DashboardActivityDependenciesComponent : DashboardActivityDependencies
}