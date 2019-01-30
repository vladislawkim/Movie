package com.vladislawfox.movie.cinema.di

import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.scope.PerScreen
import com.vladislawfox.movie.cinema.screen.MovieFragment
import com.vladislawfox.movie.dashboard.activity.DashboardActivityComponent
import dagger.Component

/**
 * Created by vladislawfox on 1/27/19.
 */
@PerScreen
@Component(
    dependencies = [DashboardActivityComponent::class],
    modules = [MovieModule::class]
)
interface MovieComponent : BaseComponent {
    fun inject(fragment: MovieFragment)
}