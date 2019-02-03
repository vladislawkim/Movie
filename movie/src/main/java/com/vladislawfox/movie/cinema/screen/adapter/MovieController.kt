package com.vladislawfox.movie.cinema.screen.adapter

import com.airbnb.epoxy.Typed2EpoxyController
import com.vladislawfox.base.presentation.adapter.carouselModel
import com.vladislawfox.movie.R
import com.vladislawfox.movie.cinema.screen.adapter.holder.MovieViewModel
import com.vladislawfox.movie.cinema.screen.adapter.holder.headerView
import com.vladislawfox.movie.cinema.screen.model.MoviesWrapper
import com.vladislawfox.movie.cinema.screen.model.ProgressWrapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/30/19.
 */
class MovieController @Inject constructor() : Typed2EpoxyController<MoviesWrapper, ProgressWrapper>() {

  override fun buildModels(movies: MoviesWrapper, progresses: ProgressWrapper) {
    headerView {
      id("headerView")
//      title(R.string.title_movie)
    }

    carouselModel {
      id("movieCarousel")
      hasFixedSize(true)
      paddingDp(16)
//            models()
    }
  }

//    private fun buildModels(list: List<String>) : List<EpoxyModel<*>> {
//        return list.map {
//        }
//    }
}