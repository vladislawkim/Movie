package com.vladislawfox.base.presentation.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by vladislawfox on 1/19/19.
 */
object ImageUtils {
    fun loadingPhoto(imageView: ImageView, uri: Uri) {
        Glide.with(imageView)
            .load(uri)
            .apply(RequestOptions
                    .diskCacheStrategyOf(DiskCacheStrategy.ALL)
                    .centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .thumbnail(0.1f)
            .into(imageView)
            .clearOnDetach()
    }
}