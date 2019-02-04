package com.vladislawfox.movie.cinema.presentation.di

import com.vladislawfox.base.presentation.di.BaseComponent
import com.vladislawfox.base.presentation.di.scope.PerScreen
import com.vladislawfox.movie.cinema.presentation.screen.MovieFragment
import com.vladislawfox.movie.di.activity.DashboardActivityComponent
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