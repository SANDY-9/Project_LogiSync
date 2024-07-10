package com.feature.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.onboard.model.OnboardPhase
import com.feature.onboard.model.OnboardUiEvent
import com.feature.onboard.model.OnboardUiState
import com.sandy.bluetooth.BluetoothState
import com.sandy.bluetooth.MyBluetoothManager
import com.sandy.bluetooth.MyWearableClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val bluetoothManager: MyBluetoothManager,
    private val wearableClient: MyWearableClient,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardUiState> = MutableStateFlow(OnboardUiState())
    internal val stateFlow: StateFlow<OnboardUiState> = _stateFlow.asStateFlow()

    private val phaseFlow = MutableStateFlow(OnboardPhase.BLUETOOTH_CONNECT)

    init {
        combine(
            bluetoothManager.getBluetoothStateFlow(), phaseFlow
        ) { bluetoothState, phase ->
            _stateFlow.value.copy(
                phase = phase,
                bluetoothState = bluetoothState,
                enabledNextButton = bluetoothState == BluetoothState.ON,
            )
        }.catch {

        }.onEach { state ->
            _stateFlow.update { state }
            when (state.phase) {
                OnboardPhase.WATCH_PAIRING_CHECK -> updateBondedWatchState()
                OnboardPhase.APP_CONNECTION -> updateIsConnectedAppState()
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    internal fun onEvent(event: OnboardUiEvent) {
        when (event) {
            is OnboardUiEvent.NavigateToNextPhase -> updatePhase()
        }
    }

    private fun updatePhase() {
        phaseFlow.update { it.nextPhase() }
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

    private fun updateIsConnectedAppState() {
        val scope = viewModelScope
        wearableClient.getWearableCapabilityStateFlow().onEach { isConnected ->
            _stateFlow.update {
                it.copy(
                    isConnectedApp = isConnected,
                    enabledNextButton = isConnected,
                    isServiceStarted = isConnected
                )
            }
            Log.e("확인", "updateIsConnectedAppState: $isConnected")
            if (isConnected) scope.cancel()
        }.launchIn(scope)
    }
}
