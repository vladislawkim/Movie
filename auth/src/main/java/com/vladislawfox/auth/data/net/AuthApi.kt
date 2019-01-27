package com.vladislawfox.auth.data.net

import com.vladislawfox.auth.data.model.GuestSessionEntity
import com.vladislawfox.auth.data.model.RequestTokenEntity
import com.vladislawfox.auth.data.model.SessionEntity
import com.vladislawfox.auth.data.request.TokenRequest
import com.vladislawfox.auth.data.request.TokenWithLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by vladislawfox on 1/18/19.
 */

const val REQUEST_TOKEN_ENDPOINT = "/3/authentication/token/new"
const val GUEST_SESSION_ENDPOINT = "/3/authentication/guest_session/new"
const val SESSION_ENDPOINT = "/3/authentication/session/new"
const val VALIDATE_TOKEN_ENDPOINT = "/3/authentication/token/validate_with_login"

interface AuthApi {

    @GET(REQUEST_TOKEN_ENDPOINT)
    fun getRequestToken(): Call<RequestTokenEntity>

    @GET(GUEST_SESSION_ENDPOINT)
    fun getGuestSession(): Call<GuestSessionEntity>

    @POST(SESSION_ENDPOINT)
    fun getSession(@Body tokenRequest: TokenRequest): Call<SessionEntity>

    @POST(VALIDATE_TOKEN_ENDPOINT)
    fun validateToken(@Body tokenRequest: TokenWithLoginRequest): Call<RequestTokenEntity>
}