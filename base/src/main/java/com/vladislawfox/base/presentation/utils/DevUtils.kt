package com.vladislawfox.base.presentation.utils

import android.util.Log
import com.vladislawfox.base.BuildConfig

object DevUtils {
    private const val TAG = "Movie"

    fun log(text: String) {
        if (isDebug()) Log.e(TAG, text)
    }
    fun log(t: Throwable) {
        if (isDebug()) t.printStackTrace()
    }

    fun isDebug() = BuildConfig.DEBUG
}