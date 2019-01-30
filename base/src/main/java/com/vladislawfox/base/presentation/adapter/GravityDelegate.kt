package com.vladislawfox.base.presentation.adapter

import android.view.Gravity
import android.view.View
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by vladislawfox on 1/30/19.
 */
internal class GravityDelegate(
    private val gravity: Int, private var snapLastItem: Boolean,
    private val listener: GravitySnapHelper.SnapListener?) {

    private var verticalHelper: OrientationHelper? = null
    private var horizontalHelper: OrientationHelper? = null
    private var isRtl: Boolean = false
    private var snapping: Boolean = false
    private var lastSnappedPosition: Int = 0
    private var recyclerView: RecyclerView? = null
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE && snapping && listener != null) {
                if (lastSnappedPosition != RecyclerView.NO_POSITION) {
                    listener.onSnap(lastSnappedPosition)
                }
                snapping = false
            }
        }
    }

    init {
        if (gravity != Gravity.START && gravity != Gravity.END
            && gravity != Gravity.BOTTOM && gravity != Gravity.TOP
        ) {
            throw IllegalArgumentException(
                "Invalid gravity value. Use START " + "| END | BOTTOM | TOP constants"
            )
        }
    }

    fun attachToRecyclerView(recyclerView: RecyclerView?) {
        recyclerView?.let {
            it.onFlingListener = null
            if (gravity == Gravity.START || gravity == Gravity.END) {
                isRtl = TextUtilsCompat.getLayoutDirectionFromLocale(
                    Locale.getDefault()
                ) == ViewCompat.LAYOUT_DIRECTION_RTL
            }
            if (listener != null) {
                it.addOnScrollListener(scrollListener)
            }
            this.recyclerView = recyclerView
        }
    }

    fun smoothScrollToPosition(position: Int) {
        scrollTo(position, true)
    }

    fun scrollToPosition(position: Int) {
        scrollTo(position, false)
    }

    private fun scrollTo(position: Int, smooth: Boolean) {
        recyclerView?.let { recyclerView ->
            recyclerView.layoutManager?.let {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                if (viewHolder != null) {
                    val distances = calculateDistanceToFinalSnap(it, viewHolder.itemView)
                    if (smooth) {
                        recyclerView.smoothScrollBy(distances[0], distances[1])
                    } else {
                        recyclerView.scrollBy(distances[0], distances[1])
                    }
                } else {
                    if (smooth) {
                        recyclerView.smoothScrollToPosition(position)
                    } else {
                        recyclerView.scrollToPosition(position)
                    }
                }
            }
        }
    }

    fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        val out = IntArray(2)

        if (layoutManager !is LinearLayoutManager) {
            return out
        }

        if (layoutManager.canScrollHorizontally()) {
            if (isRtl && gravity == Gravity.END || !isRtl && gravity == Gravity.START) {
                out[0] = distanceToStart(targetView, layoutManager, getHorizontalHelper(layoutManager))
            } else {
                out[0] = distanceToEnd(targetView, layoutManager, getHorizontalHelper(layoutManager))
            }
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            if (gravity == Gravity.TOP) {
                out[1] = distanceToStart(targetView, layoutManager, getVerticalHelper(layoutManager))
            } else { // BOTTOM
                out[1] = distanceToEnd(targetView, layoutManager, getVerticalHelper(layoutManager))
            }
        } else {
            out[1] = 0
        }

        return out
    }

    fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager !is LinearLayoutManager) {
            return null
        }
        val snapView: View? = when (gravity) {
            Gravity.START -> findEdgeView(layoutManager, getHorizontalHelper(layoutManager), true)
            Gravity.END -> findEdgeView(layoutManager, getHorizontalHelper(layoutManager), false)
            Gravity.TOP -> findEdgeView(layoutManager, getVerticalHelper(layoutManager), true)
            Gravity.BOTTOM -> findEdgeView(layoutManager, getVerticalHelper(layoutManager), false)
            else -> throw IllegalArgumentException("unsupported gravity type")
        }
        snapping = snapView != null
        snapView?.let {
            lastSnappedPosition = recyclerView?.getChildAdapterPosition(it) ?: 0
        }
        return snapView
    }

    fun enableLastItemSnap(snap: Boolean) {
        snapLastItem = snap
    }

    private fun distanceToStart(
        targetView: View, lm: LinearLayoutManager,
        helper: OrientationHelper
    ): Int {
        return recyclerView?.let {
            val pos = it.getChildLayoutPosition(targetView)
            if ((pos == 0 && (!isRtl || lm.reverseLayout) || pos == lm.itemCount - 1 && (isRtl || lm.reverseLayout)) && !it.clipToPadding) {
                val childStart = helper.getDecoratedStart(targetView)
                if (childStart >= helper.startAfterPadding / 2) {
                    childStart - helper.startAfterPadding
                } else {
                    childStart
                }
            } else {
                helper.getDecoratedStart(targetView)
            }
        } ?: 0
    }

    private fun distanceToEnd(
        targetView: View, lm: LinearLayoutManager,
        helper: OrientationHelper
    ): Int {
        return recyclerView?.let {
            val pos = it.getChildLayoutPosition(targetView)
            if ((pos == 0 && (isRtl || lm.reverseLayout) || pos == lm.itemCount - 1 && (!isRtl || lm.reverseLayout)) && !it.clipToPadding) {
                val childEnd = helper.getDecoratedEnd(targetView)
                if (childEnd >= helper.end - (helper.end - helper.endAfterPadding) / 2) {
                    helper.getDecoratedEnd(targetView) - helper.end
                } else {
                    childEnd - helper.endAfterPadding
                }
            } else {
                helper.getDecoratedEnd(targetView) - helper.end
            }
        } ?: 0
    }

    /**
     * Returns the first view that we should snap to.
     *
     * @param lm     the recyclerview's layout manager
     * @param helper orientation helper to calculate view sizes
     * @return the first view in the LayoutManager to snap to
     */
    private fun findEdgeView(
        lm: LinearLayoutManager, helper: OrientationHelper,
        start: Boolean
    ): View? {
        if (lm.childCount == 0) {
            return null
        }
        // If we're at the end of the list, we shouldn't snap
        // to avoid having the last item not completely visible.
        if (isAtEndOfList(lm) && !snapLastItem) {
            return null
        }

        var edgeView: View? = null
        var distanceToEdge = Integer.MAX_VALUE

        for (i in 0 until lm.childCount) {
            val currentView = lm.getChildAt(i)
            val currentViewDistance: Int
            currentViewDistance = if (start && !isRtl || !start && isRtl) {
                Math.abs(helper.getDecoratedStart(currentView))
            } else {
                Math.abs(helper.getDecoratedEnd(currentView) - helper.end)
            }
            if (currentViewDistance < distanceToEdge) {
                distanceToEdge = currentViewDistance
                edgeView = currentView
            }
        }
        return edgeView
    }

    private fun isAtEndOfList(lm: LinearLayoutManager): Boolean {
        return if (!lm.reverseLayout && gravity == Gravity.START || lm.reverseLayout && gravity == Gravity.END) {
            lm.findLastCompletelyVisibleItemPosition() == lm.itemCount - 1
        } else {
            lm.findFirstCompletelyVisibleItemPosition() == 0
        }
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return verticalHelper ?: OrientationHelper.createVerticalHelper(layoutManager)
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return horizontalHelper ?: OrientationHelper.createHorizontalHelper(layoutManager)
    }
}
