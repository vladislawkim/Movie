package com.vladislawfox.auth.domain.model

/**
 * Created by vladislawfox on 1/18/19.
 */
data class RequestToken(val expiresAt: String, val requestToken: String)