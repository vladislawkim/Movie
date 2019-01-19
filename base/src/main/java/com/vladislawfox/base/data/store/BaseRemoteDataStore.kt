package com.vladislawfox.base.data.store

import com.vladislawfox.base.domain.exception.Failure
import com.vladislawfox.base.domain.exception.NetworkStatusCode
import com.vladislawfox.base.domain.functional.Either
import retrofit2.Call

/**
 * Created by vladislawfox on 1/19/19.
 */
open class BaseRemoteDataStore {
    protected fun <R> request(call: Call<R>, default: R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(response.body() ?: default)
                false -> Either.Left(Failure.ServerError(response.code(), NetworkStatusCode.getErrorMessage(response.code())))
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError(NetworkStatusCode.UNKNOWN_ERROR_CODE, ""))
        }
    }
}