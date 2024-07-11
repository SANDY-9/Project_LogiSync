package com.feature.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.enums.BluetoothState
import com.core.domain.usecases.bluetooth.GetBluetoothStateUseCase
import com.core.domain.usecases.bluetooth.GetIsPairedDeviceUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.feature.onboard.model.OnboardPhase
import com.feature.onboard.model.OnboardUiEvent
import com.feature.onboard.model.OnboardUiState
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    getBluetoothStateUseCase: GetBluetoothStateUseCase,
    private val getIsPairedDeviceUseCase: GetIsPairedDeviceUseCase,
    private val getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardUiState> = MutableStateFlow(OnboardUiState())
    internal val stateFlow: StateFlow<OnboardUiState> = _stateFlow.asStateFlow()

    private val phaseFlow = MutableStateFlow(OnboardPhase.BLUETOOTH_CONNECT)

    init {
        combine(
            getBluetoothStateUseCase(),
            phaseFlow,
        ) { bluetoothState, phase ->
            val enabledNextButton =
                phase == OnboardPhase.BLUETOOTH_CONNECT && bluetoothState == BluetoothState.ON
            _stateFlow.value.copy(
                phase = phase,
                bluetoothState = bluetoothState,
                enabledNextButton = enabledNextButton,
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
        viewModelScope.launch {
            _stateFlow.update {
                val isPairedDevice = getIsPairedDeviceUseCase()
                it.copy(
                    isBondedWatch = isPairedDevice,
                    enabledNextButton = isPairedDevice,
                )
            }
        }
    }

    private fun updateIsConnectedAppState() {
        val scope = viewModelScope
        getWearableConnectStateUseCase().onEach { isConnected ->
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
