package com.feature.login.loginscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.core.firebase.UserDataSource
import com.feature.login.loginscreen.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDataSource: UserDataSource,
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
        val state = stateFlow.value
        userDataSource.login(
            state.id,
            state.pwd
        ) { account ->
            Log.e("확인", "requestLogin: $account")
        }
    }

    fun requestBioLogin() {
    }
}
