package com.vladislawfox.base.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.vladislawfox.base.BuildConfig
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */

const val PREFERENCE_NAME = BuildConfig.APPLICATION_ID + BuildConfig.FLAVOR

const val SESSION_ID_KEY = "Prefs.SESSION_ID_KEY"

class PreferenceUtils @Inject constructor(context: Context) {

    private val prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

    var sessionId: String?
        get() = prefs.getString(SESSION_ID_KEY, "")
        set(value) = prefs.edit().putString(SESSION_ID_KEY, value).apply()
}