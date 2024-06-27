package com.feature.login.loginscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.feature.login.loginscreen.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())

    val stateFlow: StateFlow<LoginUiState> = _stateFlow.asStateFlow()

    fun inputId(input: String) {
        _stateFlow.update {
            it.copy(
                id = input
            )
        }
    }

    fun inputPwd(input: String) {
        _stateFlow.update {
            it.copy(
                pwd = input
            )
        }
    }

    fun requestLogin() {

    }

    fun requestBioLogin() {

    }

}
