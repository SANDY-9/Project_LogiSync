package com.feature.signup.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface SignupUiState {

    @Immutable
    data class Phase(
        val phase: SignupStep = SignupStep.CHECK,
    )

    @Immutable
    data class Check(
        val name: String = "",
        val tel: String = "",
    ) : SignupUiState

    @Immutable
    data class Agreement(
        val isAllChecked: Boolean = false,
        val isServiceChecked: Boolean = false,
        val isServiceExpand: Boolean = false,
        val isPersonalChecked: Boolean = false,
        val isPersonalExpand: Boolean = false,
        val agreeError: Boolean = false,
    ) : SignupUiState

    @Immutable
    data class Joining(
        val id: String = "",
        val pwd: String = "",
        val pwdCheck: String = "",
        val idError: Boolean = false,
        val pwdError: Boolean = false,
    ) : SignupUiState
}

enum class SignupStep {
    CHECK, AGREEMENT, JOINING
}
