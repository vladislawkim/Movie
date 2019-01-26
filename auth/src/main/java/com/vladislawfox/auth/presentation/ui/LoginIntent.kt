package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.base.presentation.mvi.MviIntent

sealed class LoginIntent : MviIntent {
    object LogInIntent : LoginIntent()
    object GuestSessionIntent : LoginIntent()
}