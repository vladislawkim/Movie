package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.base.presentation.mvi.MviAction

sealed class LoginAction : MviAction {
    object CreateSessionIdAction : LoginAction()
    object CreateGuestSessionIdAction: LoginAction()
}