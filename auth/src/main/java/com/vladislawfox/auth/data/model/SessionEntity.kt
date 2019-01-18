package com.vladislawfox.auth.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by vladislawfox on 1/18/19.
 */
data class SessionEntity(@SerializedName("session_id") val sessionId: String) {
    companion object {
        fun empty() = SessionEntity("")
    }
}