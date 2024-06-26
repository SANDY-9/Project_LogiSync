package com.feature.signup.model

sealed interface SignupUiEvent {

    // Check
    data class InputName(val input: String) : SignupUiEvent
    data class InputTel(val input: String) : SignupUiEvent
    data object CheckSignup : SignupUiEvent

    // Agreement
    data class CheckAll(val check: Boolean) : SignupUiEvent
    data class CheckService(val check: Boolean) : SignupUiEvent
    data class CheckPersonal(val check: Boolean) : SignupUiEvent
    data class ExpandService(val expand: Boolean) : SignupUiEvent
    data class ExpandPersonal(val expand: Boolean) : SignupUiEvent
    data object CheckAgreement : SignupUiEvent

    // Joining
    data class InputId(val input: String) : SignupUiEvent
    data object CheckId : SignupUiEvent
    data class InputPwd(val input: String) : SignupUiEvent
    data class InputPwdCheck(val input: String) : SignupUiEvent
    data object CompleteSignup : SignupUiEvent
}
