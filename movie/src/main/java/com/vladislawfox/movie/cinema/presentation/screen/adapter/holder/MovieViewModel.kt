package com.vladislawfox.movie.cinema.presentation.screen.adapter.holder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.vladislawfox.base.presentation.adapter.holder.BaseHolder
import com.vladislawfox.base.presentation.utils.ImageUtils
import com.vladislawfox.movie.R


/**
 * Created by vladislawfox on 1/31/19.
 */
@EpoxyModelClass
abstract class MovieViewModel : EpoxyModelWithHolder<MovieHolder>() {
  @EpoxyAttribute lateinit var photoUri: Uri
  @EpoxyAttribute var movieRate: Double = 0.0
  @EpoxyAttribute lateinit var movieTitle: String
  @EpoxyAttribute(DoNotHash) lateinit var onMovieClickListener: View.OnClickListener
  override fun getDefaultLayout(): Int = R.layout.view_movie_model

  override fun bind(holder: MovieHolder) {
    with(holder) {
      imageView.setOnClickListener(onMovieClickListener)
      ImageUtils.loadingPhoto(imageView, photoUri)
      rateText.text = movieRate.toString()
      titleText.text = movieTitle
    }
  }

  override fun unbind(holder: MovieHolder) {
    with(holder) {
      holder.imageView.setOnClickListener(null)
      Glide.with(imageView).clear(imageView)
    }
  }
}

class MovieHolder : BaseHolder() {
  val imageView by bind<ImageView>(R.id.view_movie_model_image)
  val rateText by bind<AppCompatTextView>(R.id.view_movie_model_rate)
  val titleText by bind<AppCompatTextView>(R.id.view_movie_model_title)
}