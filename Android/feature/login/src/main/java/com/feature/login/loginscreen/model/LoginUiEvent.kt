package com.feature.login.loginscreen.model

sealed interface LoginUiEvent {
    data class InputName(val input: String) : LoginUiEvent
    data class InputTel(val input: String) : LoginUiEvent
    data object RequestLogin : LoginUiEvent
    data object ShowBioLogin : LoginUiEvent
    data object RequestBioLogin : LoginUiEvent
}
