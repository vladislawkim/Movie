package com.vladislawfox.auth.data.net

import com.vladislawfox.auth.data.model.GuestSessionEntity
import com.vladislawfox.auth.data.model.RequestTokenEntity
import com.vladislawfox.auth.data.model.SessionEntity
import com.vladislawfox.auth.data.request.TokenRequest
import com.vladislawfox.auth.data.request.TokenWithLoginRequest
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject


class AuthService @Inject constructor(retrofit: Retrofit) : AuthApi {

    private val authApi by lazy { retrofit.create(AuthApi::class.java) }

    override fun getRequestToken(): Call<RequestTokenEntity> = authApi.getRequestToken()

    override fun getGuestSession(): Call<GuestSessionEntity> = authApi.getGuestSession()

    override fun getSession(tokenRequest: TokenRequest): Call<SessionEntity> = authApi.getSession(tokenRequest)

    override fun validateToken(tokenRequest: TokenWithLoginRequest): Call<RequestTokenEntity> = authApi.validateToken(tokenRequest)
}