package com.vladislawfox.auth.data.repository

import com.vladislawfox.auth.data.mapper.GuestSessionEntityMapper
import com.vladislawfox.auth.data.mapper.RequestTokenEntityMapper
import com.vladislawfox.auth.data.mapper.SessionEntityMapper
import com.vladislawfox.auth.data.model.GuestSessionEntity
import com.vladislawfox.auth.data.model.RequestTokenEntity
import com.vladislawfox.auth.data.model.SessionEntity
import com.vladislawfox.auth.data.net.AuthService
import com.vladislawfox.auth.data.request.TokenRequest
import com.vladislawfox.auth.data.request.TokenWithLoginRequest
import com.vladislawfox.auth.domain.model.GuestSession
import com.vladislawfox.auth.domain.model.RequestToken
import com.vladislawfox.auth.domain.model.Session
import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.base.exception.Failure
import com.vladislawfox.base.exception.NetworkStatusCode
import com.vladislawfox.base.functional.Either
import com.vladislawfox.base.functional.map
import com.vladislawfox.base.utils.PreferenceUtils
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */
class AuthRepositoryImpl @Inject constructor(
    private val guestSessionEntityMapper: GuestSessionEntityMapper,
    private val requestTokenEntityMapper: RequestTokenEntityMapper,
    private val sessionEntityMapper: SessionEntityMapper,
    private val authService: AuthService,
    private val preferenceUtils: PreferenceUtils
) : AuthRepository {

    override fun getRequestToken(): Either<Failure, RequestToken> =
        request(authService.getRequestToken(), RequestTokenEntity.empty()).map { requestTokenEntityMapper.map(it) }


    override fun validateRequestToken(
        username: String,
        password: String,
        token: String
    ): Either<Failure, RequestToken> = request(
        authService.validateToken(TokenWithLoginRequest(username, password, token)),
        RequestTokenEntity.empty()
    ).map { requestTokenEntityMapper.map(it) }


    override fun getGuestSession(): Either<Failure, GuestSession> =
        request(authService.getGuestSession(), GuestSessionEntity.empty()).map { guestSessionEntityMapper.map(it) }

    override fun getSession(requestToken: String): Either<Failure, Session> = request(
        authService.getSession(TokenRequest(requestToken)),
        SessionEntity.empty()
    ).map {
        preferenceUtils.sessionId = it.sessionId
        sessionEntityMapper.map(it)
    }

    private fun <R> request(call: Call<R>, default: R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(
                    Failure.ServerError(
                        response.code(),
                        NetworkStatusCode.getErrorMessage(response.code())
                    )
                )
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(NetworkStatusCode.UNKNOWN_ERROR_CODE, ""))
        }
    }
}