package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.base.presentation.mvi.MviViewState

data class LoginViewState(val isLoading: Boolean) : MviViewState {
    companion object {
        fun idle() = LoginViewState(false)
    }
}