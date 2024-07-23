package com.feature.login.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.usecases.auth.RequestLoginUseCase
import com.feature.login.loginscreen.model.LoginError
import com.feature.login.loginscreen.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val requestLoginUseCase: RequestLoginUseCase,
    private val devicePrefsRepository: DevicePrefsRepository,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    internal val stateFlow: StateFlow<LoginUiState> get() = _stateFlow

    private val state get() = stateFlow.value

    internal fun inputId(input: String) {
        _stateFlow.value = state.copy(
            id = input
        )
    }

    internal fun clearInputId() {
        _stateFlow.value = state.copy(
            id = ""
        )
    }

    internal fun inputPwd(input: String) {
        _stateFlow.value = state.copy(
            pwd = input
        )
    }

    internal fun visiblePwd() {
        _stateFlow.value = state.copy(
            pwdVisible = !state.pwdVisible
        )
    }

    internal fun requestLogin() {
        requestLoginUseCase(
            id = state.id,
            password = state.pwd
        ).onStart {
            updateLoginState(isLoading = true, error = LoginError.NONE)
        }.onEach { account ->
            _stateFlow.value = state.copy(
                account = account,
                isLoading = false,
                error = LoginError.NONE,
            )
        }.catch { e ->
            when(e.message) {
                LoginError.WRONG_ID_OR_PWD.name -> updateLoginState(false, LoginError.WRONG_ID_OR_PWD)
                else -> updateLoginState(false, LoginError.NETWORK_ERROR)
            }
        }.launchIn(viewModelScope)
    }

    private fun updateLoginState(isLoading: Boolean, error: LoginError) {
        _stateFlow.value = state.copy(
            isLoading = isLoading,
            error = error
        )
    }

    internal suspend fun getIsInitialConnect(): Boolean {
        return devicePrefsRepository.getIsInitialConnect()
    }

    fun requestBioLogin() {
    }
}
