package com.feature.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.feature.signup.model.SignupStep
import com.feature.signup.model.SignupUiEvent
import com.feature.signup.model.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SignupUiState> =
        MutableStateFlow(SignupUiState())

    internal val stateFlow: StateFlow<SignupUiState> = _stateFlow.asStateFlow()

    internal fun onEvent(event: SignupUiEvent) {
        when (event) {
            // update input
            is SignupUiEvent.InputName -> updateInputName(event.input)
            is SignupUiEvent.InputTel -> updateInputTel(event.input)
            is SignupUiEvent.InputId -> updateInputId(event.input)
            is SignupUiEvent.InputPwd -> updateInputPwd(event.input)
            is SignupUiEvent.InputPwdCheck -> updateInputPwdCheck(event.input)

            // change
            is SignupUiEvent.ChangeAllChecked -> updateAllCheck(event.check)
            is SignupUiEvent.ChangeServiceChecked -> updateServiceCheck(event.check)
            is SignupUiEvent.ChangePersonalChecked -> updatePersonalCheck(event.check)
            is SignupUiEvent.ChangeServiceExpanded -> updateServiceExpand()
            is SignupUiEvent.ChangePersonalExpanded -> updatePersonalExpand()

            // check
            is SignupUiEvent.CheckId -> requestCheckId()

            // navigate
            is SignupUiEvent.CheckSignup -> checkSignup()
            is SignupUiEvent.CheckAgreement -> checkAgreement()
            is SignupUiEvent.RequestSignup -> requestSignup()
        }
    }

    private fun updateInputName(input: String) {
        _stateFlow.update {
            val check = it.check
            it.copy(
                check = check.copy(
                    name = input
                )
            )
        }
    }

    private fun updateInputTel(input: String) {
        _stateFlow.update {
            val check = it.check
            it.copy(
                check = check.copy(
                    tel = input
                )
            )
        }
    }

    private fun updateInputId(input: String) {
        _stateFlow.update {
            val joining = it.joining
            it.copy(
                joining = joining.copy(
                    id = input
                )
            )
        }
    }

    private fun updateInputPwd(input: String) {
        _stateFlow.update {
            val joining = it.joining
            it.copy(
                joining = joining.copy(
                    pwd = input
                )
            )
        }
    }

    private fun updateInputPwdCheck(input: String) {
        _stateFlow.update {
            val joining = it.joining
            it.copy(
                joining = joining.copy(
                    pwdCheck = input
                )
            )
        }
    }

    private fun updateAllCheck(check: Boolean) {
        _stateFlow.update {
            val agreement = it.agreement
            it.copy(
                agreement = agreement.copy(
                    isAllChecked = check,
                    isServiceChecked = check,
                    isPersonalChecked = check,
                )
            )
        }
    }

    private fun updateServiceCheck(check: Boolean) {
        _stateFlow.update {
            val agreement = it.agreement
            it.copy(
                agreement = agreement.copy(
                    isAllChecked = false,
                    isServiceChecked = check,
                )
            )
        }
    }

    private fun updatePersonalCheck(check: Boolean) {
        _stateFlow.update {
            val agreement = it.agreement
            it.copy(
                agreement = agreement.copy(
                    isAllChecked = false,
                    isPersonalChecked = check,
                )
            )
        }
    }

    private fun updateServiceExpand() {
        _stateFlow.update {
            val agreement = it.agreement
            it.copy(
                agreement = agreement.copy(
                    isServiceExpand = !agreement.isServiceExpand,
                )
            )
        }
    }

    private fun updatePersonalExpand() {
        _stateFlow.update {
            val agreement = it.agreement
            it.copy(
                agreement = agreement.copy(
                    isPersonalExpand = !agreement.isPersonalExpand,
                )
            )
        }
    }

    private fun checkSignup() {
        updatePhase(SignupStep.AGREEMENT)
    }

    private fun checkAgreement() {
        updatePhase(SignupStep.JOINING)
    }

    private fun requestCheckId() {
    }

    private fun requestSignup() {
    }

    private fun updatePhase(nextPhase: SignupStep) {
        _stateFlow.update {
            it.copy(
                phase = nextPhase
            )
        }
    }
}
