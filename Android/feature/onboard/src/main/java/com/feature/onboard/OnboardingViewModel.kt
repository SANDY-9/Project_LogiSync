package com.feature.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.enums.BluetoothState
import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.usecases.bluetooth.GetBluetoothStateUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.core.domain.usecases.wearable.LoginWearableUseCase
import com.feature.onboard.model.OnboardPhase
import com.feature.onboard.model.OnboardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getBluetoothStateUseCase: GetBluetoothStateUseCase,
    private val getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
    private val devicePrefsRepository: DevicePrefsRepository,
    private val loginWearableUseCase: LoginWearableUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<OnboardUiState> = MutableStateFlow(OnboardUiState())
    internal val stateFlow: StateFlow<OnboardUiState> = _stateFlow.asStateFlow()
    private val state get() =  stateFlow.value

    private val phaseFlow = MutableStateFlow(OnboardPhase.BLUETOOTH_CONNECT)

    init {
        phaseFlow.onEach { phase ->
            _stateFlow.value = state.copy(
                phase = phase,
            )
            when (phase) {
                OnboardPhase.BLUETOOTH_CONNECT -> updateBluetoothState()
                OnboardPhase.APP_CONNECTION -> monitorAppConnectionState()
                else -> Unit
            }
        }.launchIn(viewModelScope)

        devicePrefsRepository.getIsInitialConnectState().onEach { isInitialized ->
            if(isInitialized) _stateFlow.value = state.copy(phase = OnboardPhase.SERVICE_START)
        }.catch {
            _stateFlow.value = state.copy(loading = false)
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

    private fun monitorAppConnectionState() {
        getWearableConnectStateUseCase().onEach { isConnected ->
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

    fun startService() {
        loginWearableUseCase()
            .onStart {
                _stateFlow.value = state.copy(loading = true)
            }.onEach {
            it?.let {
                delay(500)
                devicePrefsRepository.updateInitialConnect()
            }
        }.launchIn(viewModelScope)
    }
}
