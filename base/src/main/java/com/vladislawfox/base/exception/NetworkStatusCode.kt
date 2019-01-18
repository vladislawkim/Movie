package com.vladislawfox.base.exception

/**
 * Created by vladislawfox on 1/18/19.
 */
object NetworkStatusCode {
    const val STATUS_OK = 1
    const val UPDATE_ITEM_OK = 12
    const val DELETED_ITEM_OK = 13
    const val INVALID_SERVICE_ERROR = 2
    const val AUTH_ACCESS_FAIL_ERROR = 3
    const val INVALID_FORMAT_ERROR = 4
    const val INVALID_PARAM_ERROR = 5
    const val INVALID_ID_ERROR = 6
    const val INVALID_API_KEY_ERROR = 7
    const val DUPLICATE_ENTRY_ERROR = 8
    const val SERVICE_OFFLINE_ERROR = 9
    const val SUSPEND_API_KEY_ERROR = 10
    const val INTERNAL_ERROR = 11
    const val AUTH_FAIL_ERROR = 14
    const val FAILED_ERROR = 15
    const val DEVICE_DENIED_ERROR = 16
    const val SESSION_DENIED_ERROR = 17
    const val VALIDATION_DENIED_ERROR = 18
    const val INVALID_HEADER_ERROR = 19
    const val INVALID_DATE_ERROR = 20
    const val ENTRY_NOT_FOUND_ERROR = 21
    const val INVALIDE_PAGE_ERROR = 22
    const val INVALIDE_DATE_FORMAT_ERROR = 23
    const val TIMEOUT_ERROR = 24
    const val COUNT_LIMIT_ERROR = 25
    const val LOGIN_PROVIDE_ERROR = 26
    const val MANY_APPEND_ERROR = 27
    const val INVALIDE_TIMEZONE_ERROR = 28
    const val CONFIRM_ACTION_ERROR = 29
    const val INVALIDE_LOGIN_ERROR = 30
    const val ACCOUNT_DISABLED_ERROR = 31
    const val EMAIL_NOT_VERIFIED_ERROR = 32
    const val INVALIDE_REQUEST_TOKEN_ERROR = 33
    const val RESOURCE_NOT_FOUND_ERROR = 34
    const val UNKNOWN_ERROR_CODE = -1

    fun getErrorMessage(code: Int): String =
        when (code) {
            STATUS_OK -> "Success."
            UPDATE_ITEM_OK -> "The item/record was updated successfully."
            DELETED_ITEM_OK -> "The item/record was deleted successfully."
            INVALID_SERVICE_ERROR -> "Invalid service: this service does not exist."
            AUTH_ACCESS_FAIL_ERROR -> "Authentication failed: You do not have permissions to access the service."
            INVALID_FORMAT_ERROR -> "Invalid format: This service doesn't exist in that format."
            INVALID_PARAM_ERROR -> "Invalid parameters: Your request parameters are incorrect."
            INVALID_ID_ERROR -> "Invalid id: The pre-requisite id is invalid or not found."
            INVALID_API_KEY_ERROR -> "Invalid API key: You must be granted a valid key."
            DUPLICATE_ENTRY_ERROR -> "Duplicate entry: The data you tried to submit already exists."
            SERVICE_OFFLINE_ERROR -> "Service offline: This service is temporarily offline, try again later."
            SUSPEND_API_KEY_ERROR -> "Suspended API key: Access to your account has been suspended, contact TMDb."
            INTERNAL_ERROR -> "Internal error: Something went wrong, contact TMDb."
            AUTH_FAIL_ERROR -> "Authentication failed."
            FAILED_ERROR -> "Failed."
            DEVICE_DENIED_ERROR -> "Device denied."
            SESSION_DENIED_ERROR -> "Session denied."
            VALIDATION_DENIED_ERROR -> "Validation failed."
            INVALID_HEADER_ERROR -> "Invalid accept header."
            INVALID_DATE_ERROR -> "Invalid date range: Should be a range no longer than 14 days."
            ENTRY_NOT_FOUND_ERROR -> "Entry not found: The item you are trying to edit cannot be found."
            INVALIDE_PAGE_ERROR -> "Invalid page: Pages start at 1 and max at 1000. They are expected to be an integer."
            INVALIDE_DATE_FORMAT_ERROR -> "Invalid date: Format needs to be YYYY-MM-DD."
            TIMEOUT_ERROR -> "Your request to the backend server timed out. Try again."
            COUNT_LIMIT_ERROR -> "Your request count (#) is over the allowed limit of (40)."
            LOGIN_PROVIDE_ERROR -> "You must provide a username and password."
            MANY_APPEND_ERROR -> "Too many append to response objects: The maximum number of remote calls is 20."
            INVALIDE_TIMEZONE_ERROR -> "Invalid timezone: Please consult the documentation for a valid timezone."
            CONFIRM_ACTION_ERROR -> "You must confirm this action: Please provide a confirm=true parameter."
            INVALIDE_LOGIN_ERROR -> "Invalid username and/or password: You did not provide a valid login."
            ACCOUNT_DISABLED_ERROR -> "Account disabled: Your account is no longer active. Contact TMDb if this is an error."
            EMAIL_NOT_VERIFIED_ERROR -> "Email not verified: Your email address has not been verified."
            INVALIDE_REQUEST_TOKEN_ERROR -> "Invalid request token: The request token is either expired or invalid.\n"
            RESOURCE_NOT_FOUND_ERROR -> "The resource you requested could not be found."
            else -> ""
        }
}