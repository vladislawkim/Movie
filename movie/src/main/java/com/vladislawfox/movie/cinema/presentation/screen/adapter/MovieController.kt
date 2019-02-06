package com.vladislawfox.movie.cinema.presentation.screen.adapter

import android.net.Uri
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Typed2EpoxyController
import com.vladislawfox.base.presentation.adapter.carouselView
import com.vladislawfox.base.presentation.extension.withModelsFrom
import com.vladislawfox.base.R
import com.vladislawfox.movie.cinema.domain.model.Movie
import com.vladislawfox.movie.cinema.presentation.screen.adapter.holder.MovieViewModel_
import com.vladislawfox.movie.cinema.presentation.screen.adapter.holder.headerView
import com.vladislawfox.movie.cinema.presentation.screen.model.MoviesWrapper
import com.vladislawfox.movie.cinema.presentation.screen.model.ProgressWrapper
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/30/19.
 */

const val HEADER_POPULAR_ID = "MovieController.HEADER_POPULAR_ID"
const val HEADER_TOP_ID = "MovieController.HEADER_TOP_ID"
const val HEADER_NOW_ID = "MovieController.HEADER_NOW_ID"
const val HEADER_UPCOMING_ID = "MovieController.HEADER_UPCOMING_ID"

const val CAROUSEL_POPULAR_ID = "MovieController.CAROUSEL_POPULAR_ID"
const val CAROUSEL_TOP_ID = "MovieController.CAROUSEL_TOP_ID"
const val CAROUSEL_NOW_ID = "MovieController.CAROUSEL_NOW_ID"
const val CAROUSEL_UPCOMING_ID = "MovieController.CAROUSEL_UPCOMING_ID"

class MovieController @Inject constructor() : Typed2EpoxyController<MoviesWrapper, ProgressWrapper>() {

  var movieClickListener: MovieClickListener? = null

  override fun buildModels(movies: MoviesWrapper, progresses: ProgressWrapper) {
    if (!movies.popularMovie.isNullOrEmpty()) buildMovieBlock(
        HEADER_POPULAR_ID,
        R.string.popular_movie_title,
        CAROUSEL_POPULAR_ID,
        movies.popularMovie!!
    )
    if (!movies.popularMovie.isNullOrEmpty()) buildMovieBlock(HEADER_TOP_ID, R.string.top_movie_title,
        CAROUSEL_TOP_ID, movies.topMovie!!)
    if (!movies.popularMovie.isNullOrEmpty()) buildMovieBlock(HEADER_NOW_ID, R.string.now_movie_title,
        CAROUSEL_NOW_ID, movies.nowMovie!!)
    if (!movies.popularMovie.isNullOrEmpty()) buildMovieBlock(HEADER_UPCOMING_ID,
        R.string.upcoming_movie_title, CAROUSEL_UPCOMING_ID, movies.upcomingMovie!!)
  }

  private fun buildMovieBlock(
      headerId: String, @StringRes headerTitle: Int,
      carouselId: String,
      movies: List<Movie>
  ) {
    headerView {
      id(headerId)
      titleRes(headerTitle)
    }
    carouselView {
      id(carouselId)
      hasFixedSize(true)
      onBind { _, view, _ ->
        OverScrollDecoratorHelper.setUpOverScroll(view, RecyclerView.VERTICAL)
      }
      paddingDp(16)
      withModelsFrom(movies) {
        MovieViewModel_()
            .id(it.id)
            .movieTitle(it.title)
            .movieRate(it.voteAverage)
            .photoUri(Uri.parse("https://image.tmdb.org/t/p/w500/${it.posterPath}"))
            .onMovieClickListener { _, _, _, _ -> movieClickListener?.onMovieClick(it) }
      }
    }
  }
}

interface MovieClickListener {
  fun onMovieClick(movie: Movie)
}