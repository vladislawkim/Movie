package com.vladislawfox.auth.domain.interactor

import com.vladislawfox.auth.domain.model.Session
import com.vladislawfox.auth.domain.repository.AuthRepository
import com.vladislawfox.base.exception.Failure
import com.vladislawfox.base.functional.Either
import com.vladislawfox.base.functional.flatMap
import com.vladislawfox.base.interactor.UseCase
import javax.inject.Inject

/**
 * Created by vladislawfox on 1/18/19.
 */
class GetSessionWithLoginUseCase @Inject constructor(private val authRepository: AuthRepository) :
    UseCase<Session, GetSessionWithLoginUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Session> {
        return authRepository.getRequestToken()
            .flatMap {
                authRepository.validateRequestToken(params.username, params.password, it.requestToken)
            }.flatMap {
                authRepository.getSession(it.requestToken)
            }
    }

    class Params(val username: String, val password: String)
}