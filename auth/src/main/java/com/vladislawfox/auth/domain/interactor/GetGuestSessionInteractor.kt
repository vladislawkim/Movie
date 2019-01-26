package com.vladislawfox.auth.domain.interactor

import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.auth.presentation.ui.LoginAction
import com.vladislawfox.auth.presentation.ui.LoginResult
import com.vladislawfox.base.presentation.mvi.MviInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetGuestSessionInteractor @Inject constructor(private val authRepository: AuthRepository) : MviInteractor<LoginAction, LoginResult> {

    @ExperimentalCoroutinesApi
    override fun CoroutineScope.processAction(action: LoginAction): ReceiveChannel<LoginResult> = produce {
            when (action) {
                is LoginAction.CreateGuestSessionIdAction -> produceLoginResult(true)
            }
        }

    @ExperimentalCoroutinesApi
    private suspend fun ProducerScope<LoginResult>.produceLoginResult(isLoading: Boolean) {
        send(LoginResult.CreateGuestSessionIdResult.InProgress(isLoading))
        val result = authRepository.getGuestSession()
        send(
            when {
                result.isRight-> LoginResult.CreateGuestSessionIdResult.Success("", "")
                else -> LoginResult.CreateGuestSessionIdResult.Failure(IllegalArgumentException())
            }
        )
    }
}