package com.core.firebase.utils

internal class NetworkError(message: String) : Exception(message)
internal class LoginError(message: String) : Exception(message)
internal class EmptyValueError(message: String) : Exception(message)

internal object ErrorMassage {
    const val NETWORK_ERROR_MESSAGE = "네트워크 연결상태를 확인해주세요."
    const val LOGIN_ERROR_MESSAGE = "아이디 혹은 비밀번호를 확인해주세요."
    const val EMPTY_ERROR_MESSAGE = "아이디, 비밀번호를 입력해주세요."
}
