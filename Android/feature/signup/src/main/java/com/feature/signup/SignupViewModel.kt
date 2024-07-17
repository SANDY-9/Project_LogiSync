package com.feature.signup

import androidx.lifecycle.ViewModel
import com.core.firebase.AuthClient
import com.core.model.Account
import com.feature.signup.model.AgreementType
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
            // check
            is SignupUiEvent.CheckId -> checkId()
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
                state.copy(
                    joining = joining.copy(
                        id = input,
                        isValidId = InputValidationHelper.isValidId(input),
                        existedId = if (state.joining.existedId != null) null else state.joining.existedId,
                    )
                )
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
                state.copy(
                    joining = state.joining.copy(
                        id = "",
                        isValidId = false,
                        existedId = if (state.joining.existedId != null) null else state.joining.existedId,
                    )
                )
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

    internal fun agree(checked: Boolean, type: AgreementType) {
        _stateFlow.value = when (type) {
            AgreementType.ALL -> state.copy(
                agreement = state.agreement.copy(
                    isAllChecked = checked,
                    isServiceChecked = checked,
                    isPersonalChecked = checked,
                    isAgreeComplete = checked,
                )
            )

            AgreementType.SERVICE -> state.copy(
                agreement = state.agreement.copy(
                    isAllChecked = false,
                    isServiceChecked = checked,
                    isAgreeComplete = state.agreement.isPersonalChecked && checked
                )
            )

            AgreementType.PERSONAL -> state.copy(
                agreement = state.agreement.copy(
                    isAllChecked = false,
                    isPersonalChecked = checked,
                    isAgreeComplete = state.agreement.isServiceChecked && checked
                )
            )
        }
    }

    internal fun expandContent(type: AgreementType) {
        _stateFlow.value = when (type) {
            AgreementType.SERVICE -> state.copy(
                agreement = state.agreement.copy(
                    isServiceExpand = !state.agreement.isServiceExpand
                )
            )

            AgreementType.PERSONAL -> state.copy(
                agreement = state.agreement.copy(
                    isPersonalExpand = !state.agreement.isPersonalExpand
                )
            )

            else -> state
        }
    }

    fun completeAgreement() {
        _stateFlow.value = state.copy(
            phase = SignupStep.JOINING
        )
    }

    fun checkId() {
        accountDataSource.checkId(state.joining.id) { existed ->
            _stateFlow.update {
                val joining = it.joining
                it.copy(
                    joining = joining.copy(
                        existedId = existed,
                    )
                )
            }
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
