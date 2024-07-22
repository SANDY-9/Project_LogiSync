package com.feature.login.loginscreen.model

import com.core.model.Account

internal data class LoginUiState(
    val id: String = "",
    val pwd: String = "",
    val pwdVisible: Boolean = false,
    val account: Account? = null,
    val error: LoginError = LoginError.NONE,
    val isLoading: Boolean = false,
)

internal enum class LoginError(val message: String = "") {
    NONE(""),
    WRONG_ID_OR_PWD("아이디 혹은 비밀번호를 확인해주세요."),
    NETWORK_ERROR("로그인을 할 수 없습니다. 인터넷 연결상태를 확인해주세요.")
}
