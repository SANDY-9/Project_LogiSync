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

            // check change
            is SignupUiEvent.ChangeAllChecked -> updateAllCheck(event.check)
            is SignupUiEvent.ChangeServiceChecked -> updateServiceCheck(event.check)
            is SignupUiEvent.ChangePersonalChecked -> updatePersonalCheck(event.check)
            is SignupUiEvent.ChangeServiceExpanded -> updateServiceExpand()
            is SignupUiEvent.ChangePersonalExpanded -> updatePersonalExpand()

            // navigate
            is SignupUiEvent.CheckSignup -> checkSignup()
            is SignupUiEvent.CheckAgreement -> checkAgreement()
            is SignupUiEvent.CompleteSignup -> requestSignup()
            else -> {}
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

    private fun checkSignup() {
        updatePhase(SignupStep.AGREEMENT)
    }

    private fun checkAgreement() {
        updatePhase(SignupStep.JOINING)
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
