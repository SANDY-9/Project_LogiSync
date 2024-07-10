package com.feature.login.loginscreen.model

import com.core.model.Account

data class LoginUiState(
    val id: String = "",
    val pwd: String = "",
    val account: Account? = null,
)
