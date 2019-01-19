package com.vladislawfox.base.presentation.platform

import android.content.Context
import com.vladislawfox.base.presentation.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by vladislawfox on 1/19/19.
 *
 * Injectable class which returns information about the network connection state.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}