package com.feature.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.enums.BluetoothState
import com.core.domain.usecases.bluetooth.GetBluetoothStateUseCase
import com.core.domain.usecases.bluetooth.GetIsPairedDeviceUseCase
import com.core.domain.usecases.prefs.GetLastPairedDeviceUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.core.domain.usecases.wearable.LoginWearableUseCase
import com.feature.onboard.model.OnboardPhase
import com.feature.onboard.model.OnboardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getBluetoothStateUseCase: GetBluetoothStateUseCase,
    private val getIsPairedDeviceUseCase: GetIsPairedDeviceUseCase,
    private val getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
    private val loginWearableUseCase: LoginWearableUseCase,
    getLastPairedDeviceUseCase: GetLastPairedDeviceUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardUiState> = MutableStateFlow(OnboardUiState())
    internal val stateFlow: StateFlow<OnboardUiState> = _stateFlow.asStateFlow()
    private val state get() =  stateFlow.value

    private val phaseFlow = MutableStateFlow(OnboardPhase.BLUETOOTH_CONNECT)

    init {

        getLastPairedDeviceUseCase().shareIn(
            viewModelScope,
            started = SharingStarted.Lazily
        ).onEach { device ->
            _stateFlow.value = state.copy(
                phase = if(device == null) OnboardPhase.BLUETOOTH_CONNECT else OnboardPhase.SERVICE_START
            )
        }.launchIn(viewModelScope)

        phaseFlow.onEach { phase ->
            _stateFlow.value = state.copy(
                phase = phase,
            )
            when (phase) {
                OnboardPhase.BLUETOOTH_CONNECT -> updateBluetoothState()
                OnboardPhase.WATCH_PAIRING_CHECK -> updateBondedWatchState()
                OnboardPhase.APP_CONNECTION -> updateIsConnectedAppState()
                OnboardPhase.ENABLE_SERVICE_START -> updateServiceStartEnableState()
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun updatePhase() {
        phaseFlow.value = phaseFlow.value.nextPhase()
    }

    private fun updateBluetoothState() {
        getBluetoothStateUseCase().onEach { bluetoothState ->
            _stateFlow.update {
                it.copy(
                    bluetoothState = bluetoothState,
                    enabledNextButton = bluetoothState == BluetoothState.ON
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun updateBondedWatchState() {
        viewModelScope.launch {
            val isPairedDevice = getIsPairedDeviceUseCase()
            _stateFlow.update {
                it.copy(
                    isBondedWatch = isPairedDevice,
                    enabledNextButton = isPairedDevice,
                )
            }
        }
    }

    private fun updateIsConnectedAppState() {
        getWearableConnectStateUseCase().onEach { pairedDevice ->
            val isConnected = pairedDevice != null
            _stateFlow.update {
                it.copy(
                    isConnectedApp = isConnected,
                    enabledNextButton = isConnected,
                )
            }
            if (isConnected) {
                phaseFlow.value = OnboardPhase.ENABLE_SERVICE_START
            }
        }.launchIn(viewModelScope)
    }

    private fun updateServiceStartEnableState() {
        loginWearableUseCase().onEach {
            _stateFlow.value = state.copy(
                enableServiceStart = true
            )
        }.launchIn(viewModelScope)
    }
}
