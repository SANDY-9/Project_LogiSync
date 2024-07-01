package com.feature.signup

import androidx.lifecycle.ViewModel
import com.core.firebase.UserDataSource
import com.core.model.Account
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
    private val userDataSource: UserDataSource,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SignupUiState> = MutableStateFlow(SignupUiState())
    internal val stateFlow: StateFlow<SignupUiState> = _stateFlow.asStateFlow()

    private val state get() = stateFlow.value

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
            is SignupUiEvent.CheckId -> checkId()
            is SignupUiEvent.CheckSignup -> checkSignup()
            is SignupUiEvent.CheckAgreement -> checkAgreement()

            // navigate
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
        userDataSource.checkTel(state.check.tel) { existed ->
            if (!existed) updatePhase(SignupStep.AGREEMENT)
        }
    }

    private fun checkAgreement() {
        updatePhase(SignupStep.JOINING)
    }

    private fun checkId() {
        userDataSource.checkId(state.joining.id) { existed ->

        }
    }

    private fun requestSignup() {
        userDataSource.signup(
            id = state.joining.id,
            pwd = state.joining.pwd,
            name = state.check.name,
            tel = state.check.tel,
            duty = Account.Duty.NORMAL.name
        ) {

        }
    }

    private fun updatePhase(nextPhase: SignupStep) {
        _stateFlow.update {
            it.copy(
                phase = nextPhase
            )
        }
    }
}
