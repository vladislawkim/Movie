package com.vladislawfox.base.presentation.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vladislawfox.base.presentation.functional.GlideApp

/**
 * Created by vladislawfox on 1/19/19.
 */
object ImageUtils {
    fun loadingPhoto(imageView: ImageView, uri: Uri) {
        GlideApp.with(imageView)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .centerCrop()
            .thumbnail(0.1f)
            .into(imageView)
            .clearOnDetach()
    }
}