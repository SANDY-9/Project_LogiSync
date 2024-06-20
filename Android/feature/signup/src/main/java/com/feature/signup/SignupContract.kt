package com.feature.signup

/**
 * UI State that represents SignupScreen
 **/
data class SignupState(
    val step: Int
)

/**
 * Signup Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SignupActions(
    val onClick: () -> Unit = {}
)
