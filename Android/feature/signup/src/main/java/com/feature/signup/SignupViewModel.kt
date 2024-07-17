package com.feature.signup

import androidx.lifecycle.ViewModel
import com.core.firebase.AuthClient
import com.core.model.Account
import com.feature.signup.model.InputType
import com.feature.signup.model.SignupStep
import com.feature.signup.model.SignupUiEvent
import com.feature.signup.model.SignupUiState
import com.feature.signup.utils.InputValidationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val accountDataSource: AuthClient,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SignupUiState> = MutableStateFlow(SignupUiState())
    internal val stateFlow: StateFlow<SignupUiState> = _stateFlow.asStateFlow()

    private val state get() = stateFlow.value

    internal fun onEvent(event: SignupUiEvent) {
        when (event) {

            // change
            is SignupUiEvent.ChangeAllChecked -> updateAllCheck(event.check)
            is SignupUiEvent.ChangeServiceChecked -> updateServiceCheck(event.check)
            is SignupUiEvent.ChangePersonalChecked -> updatePersonalCheck(event.check)
            is SignupUiEvent.ChangeServiceExpanded -> updateServiceExpand()
            is SignupUiEvent.ChangePersonalExpanded -> updatePersonalExpand()

            // check
            is SignupUiEvent.CheckId -> checkId()
            //is SignupUiEvent.CheckSignup -> checkSignup()
            is SignupUiEvent.CheckAgreement -> checkAgreement()

            // navigate
            is SignupUiEvent.RequestSignup -> requestSignup()
            else -> {}
        }
    }

    internal fun input(input: String, type: InputType) {
        _stateFlow.value = when (type) {
            InputType.NAME -> {
                val check = state.check
                state.copy(
                    check = check.copy(
                        name = input,
                        isInputComplete = check.tel.isNotBlank() && input.isNotBlank(),
                    )
                )
            }

            InputType.TEL -> {
                val check = state.check
                val telInput = InputValidationHelper.getValidatedTel(input)
                state.copy(
                    check = check.copy(
                        tel = telInput,
                        isInputComplete = check.name.isNotBlank() && telInput.isNotBlank(),
                    )
                )
            }

            InputType.ID -> {
                val joining = state.joining
                state.copy(joining = joining.copy(id = input))
            }

            InputType.PASSWORD -> {
                val joining = state.joining
                state.copy(joining = joining.copy(pwd = input))
            }

            InputType.PWD_CHECK -> {
                val joining = state.joining
                state.copy(joining = joining.copy(pwdCheck = input))
            }
        }
    }

    internal fun clear(type: InputType) {
        _stateFlow.value = when (type) {
            InputType.NAME -> {
                state.copy(check = state.check.copy(name = ""))
            }

            InputType.TEL -> {
                state.copy(check = state.check.copy(tel = ""))
            }

            InputType.ID -> {
                state.copy(joining = state.joining.copy(id = ""))
            }

            InputType.PASSWORD -> {
                state.copy(joining = state.joining.copy(pwd = ""))
            }

            InputType.PWD_CHECK -> {
                state.copy(joining = state.joining.copy(pwdCheck = ""))
            }
        }
    }

    internal fun checkSignup() {
        _stateFlow.value = state.copy(
            check = state.check.copy(existedId = false)
        )
        accountDataSource.checkTel(state.check.tel) { existed ->
            _stateFlow.value = _stateFlow.value.copy(
                phase = if (existed) state.phase else SignupStep.AGREEMENT,
                check = state.check.copy(existedId = existed),
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

    private fun checkAgreement() {
        updatePhase(SignupStep.JOINING)
    }

    private fun checkId() {
        accountDataSource.checkId(state.joining.id) { existed ->

        }
    }

    private fun requestSignup() {
        accountDataSource.signup(
            id = state.joining.id,
            pwd = state.joining.pwd,
            name = state.check.name,
            tel = state.check.tel,
            duty = Account.Duty.NORMAL.name
        ) {

        }
    }

    private fun updatePhase(nextPhase: SignupStep) {
        _stateFlow.value = state.copy(
            phase = nextPhase
        )
    }
}
