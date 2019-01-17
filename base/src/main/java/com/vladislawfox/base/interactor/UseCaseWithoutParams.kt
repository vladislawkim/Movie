package com.vladislawfox.base.interactor

import com.vladislawfox.base.exception.Failure
import com.vladislawfox.base.functional.Either
import kotlinx.coroutines.*

abstract class UseCaseWithoutParams<out Type> where Type : Any {
    private val mainJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mainJob)

    abstract suspend fun run(): Either<Failure, Type>

    operator fun invoke(onResult: (Either<Failure, Type>) -> Unit = {}) {
        uiScope.launch {
            val job = async(Dispatchers.IO) { run() }
            onResult(job.await())
        }
    }
}