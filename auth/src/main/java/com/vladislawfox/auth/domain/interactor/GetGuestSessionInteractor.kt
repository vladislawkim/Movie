package com.vladislawfox.auth.domain.interactor

import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.auth.presentation.ui.LoginAction
import com.vladislawfox.auth.presentation.ui.LoginResult
import com.vladislawfox.base.presentation.mvi.MviInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import javax.inject.Inject

class GetGuestSessionInteractor @Inject constructor(private val authRepository: AuthRepository) : MviInteractor<LoginAction, LoginResult> {

    private val mainJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

    @ExperimentalCoroutinesApi
    override fun CoroutineScope.processAction(action: LoginAction): ReceiveChannel<LoginResult> = produce {
            when (action) {
                is LoginAction.CreateGuestSessionIdAction -> produceLoginResult(true)
            }
        }

    @ExperimentalCoroutinesApi
    private suspend fun ProducerScope<LoginResult>.produceLoginResult(isLoading: Boolean) {
        send(LoginResult.CreateGuestSessionIdResult.InProgress(isLoading))
        uiScope.launch {
            val job = async(Dispatchers.IO) {
                authRepository.getGuestSession().either(fnL = {
                    LoginResult.CreateGuestSessionIdResult.Failure(it)
                }, fnR = {
                    LoginResult.CreateGuestSessionIdResult.Success(it.sessionId, it.expiresAt)
                }) as LoginResult
            }
            send(job.await())
        }
//        val answer = result.either(fnL = {
//            LoginResult.CreateGuestSessionIdResult.Failure(it)
//        }, fnR = {
//           LoginResult.CreateGuestSessionIdResult.Success(it.sessionId, it.expiresAt)
//        }) as LoginResult
//        send(answer)
    }
}