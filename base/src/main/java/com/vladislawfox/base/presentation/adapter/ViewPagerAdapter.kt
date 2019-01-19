package com.vladislawfox.base.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Created by vladislawfox on 1/19/19.
 */
abstract class ViewPagerAdapter : PagerAdapter() {

    abstract fun getView(position: Int, pager: ViewPager): View

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view === any
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = getView(position, container as ViewPager)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}