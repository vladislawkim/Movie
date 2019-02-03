package com.vladislawfox.movie.cinema.screen

import androidx.recyclerview.widget.LinearLayoutManager
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.mvp.BaseFragment
import com.vladislawfox.movie.R
import com.vladislawfox.movie.cinema.contract.MovieContract
import com.vladislawfox.movie.cinema.di.DaggerMovieComponent
import com.vladislawfox.movie.cinema.di.MovieComponent
import com.vladislawfox.movie.cinema.screen.adapter.MovieController
import com.vladislawfox.movie.cinema.screen.model.MoviesWrapper
import com.vladislawfox.movie.cinema.screen.model.ProgressWrapper
import com.vladislawfox.movie.dashboard.activity.DashboardActivityComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment<MovieContract.View, MovieContract.Presenter, MovieComponent>(
    R.layout.fragment_movie
), MovieContract.View {

  @Inject lateinit var movieController: MovieController

  override fun onResume() {
    super.onResume()
    viewMovieList.layoutManager = LinearLayoutManager(context)
    viewMovieList.adapter = movieController.adapter
    movieController.setData(MoviesWrapper(), ProgressWrapper())
  }

  override fun initializeInjector() {
    this.viewComponent = DaggerMovieComponent.builder()
        .dashboardActivityComponent(
            (activity as HasComponent<*>).getComponent() as DashboardActivityComponent)
        .build()
    viewComponent.inject(this)
  }
}
