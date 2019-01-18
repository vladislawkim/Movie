package com.vladislawfox.auth.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by vladislawfox on 1/18/19.
 */
data class GuestSessionEntity(
    @SerializedName("guest_session_id") val sessionId: String,
    @SerializedName("expires_at") val expiresAt: String
) {
    companion object {
        fun empty() = GuestSessionEntity("", "")
    }
}