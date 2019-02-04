package com.vladislawfox.movie.cinema.presentation.screen

import androidx.recyclerview.widget.LinearLayoutManager
import com.vladislawfox.base.presentation.di.HasComponent
import com.vladislawfox.base.presentation.mvp.BaseFragment
import com.vladislawfox.movie.R
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.presentation.contract.MovieContract
import com.vladislawfox.movie.cinema.presentation.di.DaggerMovieComponent
import com.vladislawfox.movie.cinema.presentation.di.MovieComponent
import com.vladislawfox.movie.cinema.presentation.screen.adapter.MovieController
import com.vladislawfox.movie.cinema.presentation.screen.model.MoviesWrapper
import com.vladislawfox.movie.cinema.presentation.screen.model.ProgressWrapper
import com.vladislawfox.movie.di.activity.DashboardActivityComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment<MovieContract.View, MovieContract.Presenter, MovieComponent>(
    R.layout.fragment_movie
), MovieContract.View {

    @Inject
    lateinit var movieController: MovieController

    override fun onResume() {
        super.onResume()
        presenter.requestData()
        viewMovieList.layoutManager = LinearLayoutManager(context)
        viewMovieList.adapter = movieController.adapter
    }

    override fun showData(list: List<Movie>) {
        movieController.setData(MoviesWrapper(list), ProgressWrapper())
    }

    override fun showData(movies: MoviesWrapper) {
        movieController.setData(movies, ProgressWrapper())
    }

    override fun initializeInjector() {
        this.viewComponent = DaggerMovieComponent.builder()
            .dashboardActivityComponent(
                (activity as HasComponent<*>).getComponent() as DashboardActivityComponent
            )
            .build()
        viewComponent.inject(this)
    }
}
