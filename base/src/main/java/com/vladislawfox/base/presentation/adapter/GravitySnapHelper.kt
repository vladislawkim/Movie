package com.vladislawfox.base.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by vladislawfox on 1/30/19.
 */
class GravitySnapHelper(gravity: Int, enableSnapLastItem: Boolean = false, snapListener: SnapListener? = null) :
    LinearSnapHelper() {

    private val delegate: GravityDelegate = GravityDelegate(
        gravity, enableSnapLastItem, snapListener
    )

    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        delegate.attachToRecyclerView(recyclerView)
        super.attachToRecyclerView(recyclerView)
    }

    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        return delegate.calculateDistanceToFinalSnap(layoutManager, targetView)
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return delegate.findSnapView(layoutManager)
    }

    /**
     * Enable snapping of the last item that's snappable.
     * The default value is false, because you can't see the last item completely
     * if this is enabled.
     *
     * @param snap true if you want to enable snapping of the last snappable item
     */
    fun enableLastItemSnap(snap: Boolean) {
        delegate.enableLastItemSnap(snap)
    }

    fun smoothScrollToPosition(position: Int) {
        delegate.smoothScrollToPosition(position)
    }

    fun scrollToPosition(position: Int) {
        delegate.scrollToPosition(position)
    }

    interface SnapListener {
        fun onSnap(position: Int)
    }
}