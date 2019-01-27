package com.vladislawfox.movie.cinema

import com.vladislawfox.base.presentation.mvp.BaseFragment
import com.vladislawfox.movie.R
import com.vladislawfox.movie.cinema.di.MovieComponent

class MovieFragment : BaseFragment<MovieContract.View, MovieContract.Presenter, MovieComponent>(
    R.layout.fragment_movie), MovieContract.View {

  override fun initializeInjector() {

  }
}
