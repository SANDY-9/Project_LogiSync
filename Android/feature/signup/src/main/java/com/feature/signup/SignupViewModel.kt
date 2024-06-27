package com.feature.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.feature.signup.model.SignupUiEvent
import com.feature.signup.model.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            is SignupUiEvent.CheckSignup -> {}
            is SignupUiEvent.CheckAgreement -> {}
            is SignupUiEvent.CompleteSignup -> {}
            else -> {}
        }
    }
}
