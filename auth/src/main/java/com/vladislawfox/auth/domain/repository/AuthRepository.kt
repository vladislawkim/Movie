package com.vladislawfox.auth.domain.repository

import com.vladislawfox.auth.domain.model.GuestSession
import com.vladislawfox.auth.domain.model.RequestToken
import com.vladislawfox.auth.domain.model.Session
import com.vladislawfox.base.exception.Failure
import com.vladislawfox.base.functional.Either

interface AuthRepository {
    fun getRequestToken(): Either<Failure, RequestToken>
    fun validateRequestToken(username: String, password: String, token: String): Either<Failure, RequestToken>
    fun getGuestSession(): Either<Failure, GuestSession>
    fun getSession(requestToken: String): Either<Failure, Session>
}