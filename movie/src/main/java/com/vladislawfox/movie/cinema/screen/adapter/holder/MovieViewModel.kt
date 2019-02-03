package com.vladislawfox.movie.cinema.screen.adapter.holder

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.vladislawfox.base.presentation.adapter.holder.BaseHolder
import com.vladislawfox.movie.R

/**
 * Created by vladislawfox on 1/31/19.
 */
@EpoxyModelClass(layout = R.layout.view_movie_model)
abstract class MovieViewModel : EpoxyModelWithHolder<Holder>() {
//    override fun getDefaultLayout(): Int = R.layout.view_movie_model
}

class Holder : BaseHolder()