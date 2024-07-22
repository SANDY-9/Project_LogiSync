package com.feature.admin.details

import androidx.lifecycle.ViewModel
import com.feature.admin.details.model.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(

): ViewModel() {

    private val _stateFlow: MutableStateFlow<UserDetailsUiState> = MutableStateFlow(UserDetailsUiState())
    internal val stateFlow: StateFlow<UserDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

}
