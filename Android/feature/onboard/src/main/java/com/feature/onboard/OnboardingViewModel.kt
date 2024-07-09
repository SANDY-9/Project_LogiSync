package com.feature.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.onboard.model.OnboardUiEvent
import com.feature.onboard.model.OnboardUiState
import com.sandy.bluetooth.BluetoothState
import com.sandy.bluetooth.MyBluetoothManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val bluetoothManager: MyBluetoothManager,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardUiState> = MutableStateFlow(OnboardUiState())
    internal val stateFlow: StateFlow<OnboardUiState> = _stateFlow.asStateFlow()

    init {
        bluetoothManager.getBluetoothStateFlow().onEach { bluetoothState ->
            Log.e("확인", "$bluetoothState: ")
            _stateFlow.update {
                var isBonedWatch = false
                if (bluetoothState == BluetoothState.ON) {
                    isBonedWatch = bluetoothManager.isBondedWatch()
                }
                it.copy(
                    bluetoothState = bluetoothState,
                    enabledNextButton = bluetoothState == BluetoothState.ON,
                    isBondedWatch = isBonedWatch,
                )
            }
        }.launchIn(viewModelScope)
    }

    internal fun onEvent(event: OnboardUiEvent) {
        when (event) {
            is OnboardUiEvent.NavigateToNextPhase -> updatePhase()
        }
    }

    private fun updatePhase() {
        _stateFlow.update {
            it.copy(
                phase = it.phase.nextPhase()
            )
        }
    }
}
