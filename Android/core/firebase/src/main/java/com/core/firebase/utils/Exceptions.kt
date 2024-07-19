package com.core.firebase.utils

internal class NetworkError(message: String) : Exception(message)
internal class LoginError(message: String) : Exception(message)
internal class EmptyValueError(message: String) : Exception(message)

internal object ErrorMassage {
    const val NETWORK_ERROR_MESSAGE = "NETWORK_ERROR"
    const val LOGIN_ERROR_MESSAGE = "WRONG_ID_OR_PWD"
    const val EMPTY_ERROR_MESSAGE = "EMPTY_ID_OR_PWD"
}
