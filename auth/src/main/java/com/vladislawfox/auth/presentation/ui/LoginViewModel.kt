package com.vladislawfox.auth.presentation.ui

import com.vladislawfox.auth.domain.interactor.GetGuestSessionInteractor
import com.vladislawfox.auth.domain.model.GuestSession
import com.vladislawfox.base.presentation.mvi.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val getGuestSessionInteractor: GetGuestSessionInteractor) :
    BaseViewModel<LoginIntent, LoginAction, LoginResult, LoginViewState>() {

    override val reduce = { previousState: LoginViewState, result: LoginResult ->
        when (result) {
            is LoginResult.CreateGuestSessionIdResult.Success -> {
                previousState.copy(isLoading = false, guestSession = GuestSession(result.sessionId, result.expiredTime))
            }
            is LoginResult.CreateGuestSessionIdResult.Failure -> {
                previousState.copy(isLoading = false)
            }
            is LoginResult.CreateGuestSessionIdResult.InProgress -> {
                previousState.copy(isLoading = true)
            }
            is LoginResult.CreateSessionIdResult.Success -> {
                previousState.copy(isLoading = true)
            }
            is LoginResult.CreateSessionIdResult.Failure -> {
                previousState.copy(isLoading = true)
            }
            is LoginResult.CreateSessionIdResult.InProgress -> {
                previousState.copy(isLoading = true)
            }
        }
    }

    override fun actionFromIntent(intent: LoginIntent): LoginAction = when (intent) {
        is LoginIntent.GuestSessionIntent -> LoginAction.CreateGuestSessionIdAction
        else -> throw IllegalArgumentException("")
    }

    @ExperimentalCoroutinesApi
    override val state = ConflatedBroadcastChannel(LoginViewState.idle())

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override suspend fun CoroutineScope.processIntents(channel: Channel<LoginIntent>) =
        coroutineScope {
            state.run {
                channel
                    .map { intent -> actionFromIntent(intent) }
                    .flatMap { action -> getGuestSessionInteractor.run { processAction(action) } }
                    .consumeEach { result -> offer(reduce(value, result)) }
            }
        }
}