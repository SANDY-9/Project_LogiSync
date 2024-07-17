package com.feature.signup.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal data class SignupUiState(
    val phase: SignupStep = SignupStep.CHECK,
    val check: CheckState = CheckState(
        name = "",
        tel = "",
        isInputComplete = false,
        existedId = false,
    ),
    val agreement: AgreementState = AgreementState(
        isAllChecked = false,
        isServiceChecked = false,
        isServiceExpand = false,
        isPersonalChecked = false,
        isPersonalExpand = false,
        isAgreeComplete = false,
    ),
    val joining: JoiningState = JoiningState(
        id = "",
        pwd = "",
        pwdCheck = "",
        idError = false,
        pwdError = false,
        pwdCheckError = false,
    )
)

@Immutable
internal data class CheckState(
    val name: String,
    val tel: String,
    val isInputComplete: Boolean,
    val existedId: Boolean,
)

@Immutable
internal data class AgreementState(
    val isAllChecked: Boolean,
    val isServiceChecked: Boolean,
    val isServiceExpand: Boolean,
    val isPersonalChecked: Boolean,
    val isPersonalExpand: Boolean,
    val isAgreeComplete: Boolean,
)

@Immutable
internal data class JoiningState(
    val id: String,
    val pwd: String,
    val pwdCheck: String,
    val idError: Boolean,
    val pwdError: Boolean,
    val pwdCheckError: Boolean,
)

internal enum class SignupStep {
    CHECK, AGREEMENT, JOINING
}

internal enum class InputType {
    NAME, TEL, ID, PASSWORD, PWD_CHECK
}

internal enum class AgreementType {
    ALL, SERVICE, PERSONAL,
}
