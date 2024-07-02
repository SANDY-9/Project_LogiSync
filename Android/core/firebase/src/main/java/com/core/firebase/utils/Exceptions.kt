package com.core.firebase.utils

import java.io.IOException

internal class NetworkError(message: String) : IOException(message)
internal class LoginError(message: String) : IOException(message)

internal object ErrorMassage {
    const val NETWORK_ERROR_MESSAGE = "네트워크 연결상태를 확인해주세요."
    const val LOGIN_ERROR_MESSAGE = "아이디 혹은 비밀번호를 확인해주세요."
}
