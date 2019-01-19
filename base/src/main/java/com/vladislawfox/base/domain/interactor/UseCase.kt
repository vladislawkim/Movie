package com.vladislawfox.base.domain.interactor

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.functional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {
    private val mainJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        uiScope.launch {
            val job = async(Dispatchers.IO) { run(params) }
            onResult(job.await())
        }
    }
}
