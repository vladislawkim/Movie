package com.vladislawfox.movie.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.mvp.BaseFragment
import com.vladislawfox.movie.R
import com.vladislawfox.movie.cinema.di.DaggerMovieComponent
import com.vladislawfox.movie.cinema.di.MovieComponent
import com.vladislawfox.movie.dashboard.activity.DashboardActivityComponent

class MovieFragment : BaseFragment<MovieContract.View, MovieContract.Presenter, MovieComponent>(
    R.layout.fragment_movie
), MovieContract.View {

    override fun initializeInjector() {
        this.viewComponent = DaggerMovieComponent.builder()
            .dashboardActivityComponent((activity as HasComponent<*>).getComponent() as DashboardActivityComponent)
            .build()
        viewComponent.inject(this)
    }
}
