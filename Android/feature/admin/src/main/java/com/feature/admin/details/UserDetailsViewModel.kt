package com.feature.admin.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetLastHeartRateListUseCase
import com.core.domain.usecases.network.GetLastMyArrestUseCase
import com.core.model.User
import com.feature.admin.details.model.UserDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getLastMyArrestUseCase: GetLastMyArrestUseCase,
    private val getLastHeartRateListUseCase: GetLastHeartRateListUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<UserDetailsUiState> = MutableStateFlow(UserDetailsUiState())
    internal val stateFlow: StateFlow<UserDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    fun getUserDetails(user: User) {
        Log.e("확인", "getUserDetails: $user", )
        _stateFlow.value = state.copy(user = user)
        val id = user.id
        combine(
            getLastMyArrestUseCase(id),
            getLastHeartRateListUseCase(id)
        ) { lastMyArrest, lastHearRateList ->
            state.copy(
                lastReportList = lastMyArrest,
                lastHeartRateList = lastHearRateList
            )
        }.onEach {  state ->
            _stateFlow.update { state }
        }.launchIn(viewModelScope)
    }

}
