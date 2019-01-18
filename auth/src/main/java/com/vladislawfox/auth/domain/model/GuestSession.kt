package com.vladislawfox.auth.domain.model

/**
 * Created by vladislawfox on 1/18/19.
 */
data class GuestSession(val sessionId: String, val expiresAt: String)