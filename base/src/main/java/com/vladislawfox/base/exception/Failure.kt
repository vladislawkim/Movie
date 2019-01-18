package com.vladislawfox.base.exception

sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val code: Int, val message: String) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}