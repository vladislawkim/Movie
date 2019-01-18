package com.vladislawfox.auth.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by vladislawfox on 1/18/19.
 */
data class RequestTokenEntity(@SerializedName("expires_at") val expiresAt: String,
                              @SerializedName("request_token") val requestToken: String) {
    companion object {
        fun empty() = RequestTokenEntity("", "")
    }
}