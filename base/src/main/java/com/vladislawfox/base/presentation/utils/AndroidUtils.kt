package com.vladislawfox.base.presentation.utils

import android.content.res.Resources

object AndroidUtils {
    fun pxToDp(px: Int) = px / Resources.getSystem().displayMetrics.density.toInt()
    fun dpToPx(dp: Int) = dp * Resources.getSystem().displayMetrics.density.toInt()
}