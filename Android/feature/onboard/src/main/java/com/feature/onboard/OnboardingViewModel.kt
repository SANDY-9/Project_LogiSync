package com.feature.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.onboard.model.OnboardPhase
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

    private val phase = MutableStateFlow(OnboardPhase.BLUETOOTH_CONNECT)

    init {
        bluetoothManager.getBluetoothStateFlow().onEach { bluetoothState ->
            _stateFlow.update {
                it.copy(
                    bluetoothState = bluetoothState,
                    enabledNextButton = bluetoothState == BluetoothState.ON,
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
            val nextPhase = it.phase.nextPhase()
            it.copy(
                phase = nextPhase,
            ).also {
                when (nextPhase) {
                    OnboardPhase.WATCH_PAIRING_CHECK -> updateBondedWatchState()
                    OnboardPhase.WATCH_CONNECTION -> bluetoothManager.findDevice()
                    else -> Unit
                }
            }
        }
    }

    private fun updateBondedWatchState() {
        _stateFlow.update {
            val isBondedWatch = bluetoothManager.isBondedWatch()
            it.copy(
                isBondedWatch = isBondedWatch,
                enabledNextButton = isBondedWatch,
            )
        }
    }
}
