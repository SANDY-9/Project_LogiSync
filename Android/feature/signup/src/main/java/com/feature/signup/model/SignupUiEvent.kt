package com.feature.signup.model

internal sealed interface SignupUiEvent {

    // Check
    data class InputName(val input: String) : SignupUiEvent
    data class InputTel(val input: String) : SignupUiEvent
    data object CheckSignup : SignupUiEvent

    // Agreement
    data class ChangeAllChecked(val check: Boolean) : SignupUiEvent
    data class ChangeServiceChecked(val check: Boolean) : SignupUiEvent
    data class ChangePersonalChecked(val check: Boolean) : SignupUiEvent
    data object ChangeServiceExpanded : SignupUiEvent
    data object ChangePersonalExpanded : SignupUiEvent
    data object CheckAgreement : SignupUiEvent

    // Joining
    data class InputId(val input: String) : SignupUiEvent
    data object CheckId : SignupUiEvent
    data class InputPwd(val input: String) : SignupUiEvent
    data class InputPwdCheck(val input: String) : SignupUiEvent
    data object CompleteSignup : SignupUiEvent
}
