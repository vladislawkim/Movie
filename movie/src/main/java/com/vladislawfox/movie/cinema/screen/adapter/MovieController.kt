package com.vladislawfox.movie.cinema.screen.adapter

import com.airbnb.epoxy.Typed2EpoxyController
import com.vladislawfox.movie.cinema.screen.model.MoviesWrapper
import com.vladislawfox.movie.cinema.screen.model.ProgressWrapper
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/30/19.
 */
class MovieController @Inject constructor() : Typed2EpoxyController<MoviesWrapper, ProgressWrapper>() {

    override fun buildModels(movies: MoviesWrapper, progresses: ProgressWrapper) {

    }
}