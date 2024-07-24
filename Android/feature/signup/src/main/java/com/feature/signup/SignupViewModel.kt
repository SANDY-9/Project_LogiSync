package com.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.usecases.auth.GetIsExistId
import com.core.domain.usecases.auth.GetIsExistMember
import com.core.domain.usecases.auth.RequestSignupUseCase
import com.feature.signup.model.AgreementType
import com.feature.signup.model.InputType
import com.feature.signup.model.SignupStep
import com.feature.signup.model.SignupUiState
import com.feature.signup.utils.InputValidationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val getIsExistMember: GetIsExistMember,
    private val getIsExistId: GetIsExistId,
    private val requestSignupUseCase: RequestSignupUseCase,
    private val devicePrefsRepository: DevicePrefsRepository,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SignupUiState> = MutableStateFlow(SignupUiState())
    internal val stateFlow: StateFlow<SignupUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

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
                state.copy(
                    joining = joining.copy(
                        pwd = input,
                        isNotValidPwd = !InputValidationHelper.isValidPassword(input),
                    )
                )
            }
            InputType.PWD_CHECK -> {
                val joining = state.joining
                val isNotValidPwdCheck = joining.pwd.isNotBlank() && input != joining.pwd
                state.copy(
                    joining = joining.copy(
                        pwdCheck = input,
                        isNotValidPwdCheck = isNotValidPwdCheck,
                    )
                )
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
            else -> {  // InputType.ID
                state.copy(
                    joining = state.joining.copy(
                        id = "",
                        isValidId = false,
                        existedId = if (state.joining.existedId != null) null else state.joining.existedId,
                    )
                )
            }
        }
    }

    internal fun checkSignup() {
        getIsExistMember(state.check.tel)
            .onStart {
                _stateFlow.value = state.copy(loading = true)
            }
            .onEach { exist ->
                _stateFlow.value = _stateFlow.value.copy(
                    loading = false,
                    phase = if (exist) state.phase else SignupStep.AGREEMENT,
                    check = state.check.copy(existedId = exist),
                )
            }.catch {
                notifyNetworkError()
                _stateFlow.value = state.copy(
                    loading = false,
                )
            }.launchIn(viewModelScope)
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

    internal fun completeAgreement() {
        viewModelScope.launch {
            _stateFlow.value = state.copy(loading = true)
            delay(700L)
            _stateFlow.value = state.copy(
                loading = false,
                phase = SignupStep.JOINING,
            )
        }
    }

    internal fun checkId() {
        getIsExistId(state.joining.id).onEach { exist ->
            _stateFlow.value = state.copy(
                joining = state.joining.copy(
                    existedId = exist
                )
            )
        }.catch {
            notifyNetworkError()
        }.launchIn(viewModelScope)
    }

    internal fun changePwdVisible(type: InputType) {
        _stateFlow.value = if (type == InputType.PASSWORD) {
            state.copy(
                joining = state.joining.copy(
                    pwdVisible = !state.joining.pwdVisible,
                )
            )
        }
        else {
            state.copy(
                joining = state.joining.copy(
                    pwdCheckVisible = !state.joining.pwdCheckVisible,
                )
            )
        }
    }

    internal fun requestSignup() {
        requestSignupUseCase(
            id = state.joining.id,
            pwd = state.joining.pwd,
            name = state.check.name,
            tel = state.check.tel,
        ).onStart {
            _stateFlow.value = state.copy(loading = true)
        }.onEach {
            _stateFlow.value = state.copy(
                signupComplete = true,
                loading = false,
            )
        }.catch {
            notifyNetworkError()
            _stateFlow.value = state.copy(
                loading = false,
            )
        }.launchIn(viewModelScope)
    }

    private fun notifyNetworkError() {
        _stateFlow.update {
            it.copy(error = true)
        }
        _stateFlow.update {
            it.copy(error = false)
        }
    }

    internal suspend fun getIsInitialConnect(): Boolean {
        return devicePrefsRepository.getIsInitialConnect()
    }

}
