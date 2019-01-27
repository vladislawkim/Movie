package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.auth.domain.model.GuestSession
import com.vladislawfox.base.presentation.mvi.MviViewState

data class LoginViewState(val isLoading: Boolean, val guestSession: GuestSession? = null) : MviViewState {
    companion object {
        fun idle() = LoginViewState(false, null)
    }
}