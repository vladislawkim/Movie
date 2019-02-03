package com.vladislawfox.base.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

/**
 * Created by vladislawfox on 1/31/19.
 */
@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
open class CarouselView(context: Context) : Carousel(context) {

  private lateinit var linearLayoutManager: LinearLayoutManager

  override fun init() {
    setBackgroundColor(Color.TRANSPARENT)
    Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
      override fun buildSnapHelper(context: Context): SnapHelper {
        return GravitySnapHelper(Gravity.START, true)
      }
    })
    super.init()
  }

  override fun createLayoutManager(): RecyclerView.LayoutManager {
    return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
      setClipToPadding(false)
      linearLayoutManager = this
    }
  }
}