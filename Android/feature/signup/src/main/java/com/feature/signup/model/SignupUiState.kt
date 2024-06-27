package com.feature.signup.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
internal data class SignupUiState(
    val phase: SignupStep = SignupStep.CHECK,
    val check: CheckState = CheckState(
        name = "",
        tel = "",
    ),
    val agreement: AgreementState = AgreementState(
        isAllChecked = false,
        isServiceChecked = false,
        isServiceExpand = false,
        isPersonalChecked = false,
        isPersonalExpand = false,
        agreeError = false,
    ),
    val joining: JoiningState = JoiningState(
        id = "",
        pwd = "",
        pwdCheck = "",
        idError = false,
        pwdError = false,
    )
)

@Immutable
internal data class CheckState(
    val name: String,
    val tel: String,
)

@Immutable
internal data class AgreementState(
    val isAllChecked: Boolean,
    val isServiceChecked: Boolean,
    val isServiceExpand: Boolean,
    val isPersonalChecked: Boolean,
    val isPersonalExpand: Boolean,
    val agreeError: Boolean,
)

@Immutable
internal data class JoiningState(
    val id: String,
    val pwd: String,
    val pwdCheck: String,
    val idError: Boolean,
    val pwdError: Boolean,
)

enum class SignupStep {
    CHECK, AGREEMENT, JOINING
}
