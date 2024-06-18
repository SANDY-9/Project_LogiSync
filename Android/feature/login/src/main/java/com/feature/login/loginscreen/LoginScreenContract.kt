package com.feature.login.loginscreen

import androidx.compose.runtime.Composable

/**
 * UI State that represents LoginScreenScreen
 **/
data class LoginScreenState(
    val id: String = "",
    val pwd: String = "",
)

/**
 * LoginScreen Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class LoginScreenActions(
    val onClick: () -> Unit = {},
)
