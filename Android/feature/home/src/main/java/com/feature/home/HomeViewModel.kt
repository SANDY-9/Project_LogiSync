package com.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.prefs.GetPairedDeviceNameUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.feature.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
    getPairedDeviceNameUseCase: GetPairedDeviceNameUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    internal val stateFlow: StateFlow<HomeUiState> = _stateFlow.asStateFlow()

    init {
        combine(
            getWearableConnectStateUseCase(),
            getPairedDeviceNameUseCase(),
        ) { isPairedWatch, pairedDeviceName ->
            _stateFlow.value.copy(
                isPairedWatch = isPairedWatch,
                pairedDeviceName = pairedDeviceName
            )
        }.onEach {
            _stateFlow.value = it
        }.launchIn(viewModelScope)
    }
}
