package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.base.presentation.mvi.MviResult

sealed class LoginResult : MviResult {
    sealed class CreateSessionIdResult : LoginResult() {
        data class Success(val sessionId: String) : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
        data class InProgress(val isLoading: Boolean) : LoginResult()
    }
    sealed class CreateGuestSessionIdResult : LoginResult() {
        data class Success(val sessionId: String, val expiredTime: String) : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
        data class InProgress(val isLoading: Boolean) : LoginResult()
    }
}