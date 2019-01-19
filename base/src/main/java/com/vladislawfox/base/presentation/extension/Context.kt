package com.vladislawfox.base.presentation.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.inputmethod.InputMethodManager

val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

val Context.inputMethodManager: InputMethodManager?
    get() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
