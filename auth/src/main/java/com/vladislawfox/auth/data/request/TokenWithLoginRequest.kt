package com.vladislawfox.auth.data.request

import com.google.gson.annotations.SerializedName

/**
 * Created by vladislawfox on 1/18/19.
 */
class TokenWithLoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("request_token") val requestToken: String
)