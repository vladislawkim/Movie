package com.vladislawfox.auth.data.request

import com.google.gson.annotations.SerializedName

/**
 * Created by vladislawfox on 1/18/19.
 */
class TokenRequest (@SerializedName("request_token") val request: String)