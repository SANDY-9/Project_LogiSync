package com.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.prefs.GetLastPairedDeviceUseCase
import com.core.domain.usecases.wearable.CollectHeartRateUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.core.domain.usecases.wearable.LoginWearableUseCase
import com.core.model.HeartRate
import com.feature.home.model.HomeUiState
import com.sandy.service.MyWearableListenerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
    getLastPairedDeviceUseCase: GetLastPairedDeviceUseCase,
    private val loginWearableUseCase: LoginWearableUseCase,
    private val collectHeartRateUseCase: CollectHeartRateUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    internal val stateFlow: StateFlow<HomeUiState> = _stateFlow.asStateFlow()

    init {
        combine(
            getWearableConnectStateUseCase(),
            getLastPairedDeviceUseCase(),
        ) { pairedWatch, pairedDevice ->
            _stateFlow.value.copy(
                isPairedWatch = pairedWatch != null,
                pairedDeviceName = pairedDevice.alias
            )
        }.onEach {
            _stateFlow.value = it
        }.launchIn(viewModelScope)

        MyWearableListenerService.heartRate.onEach { rate ->
            _stateFlow.update {
                it.copy(
                    heartRate = HeartRate(bpm = rate)
                )
            }
        }.launchIn(viewModelScope)
    }

    fun requestCollectHeartBeat() {
        flow {
            emit(collectHeartRateUseCase(""))
        }.catch {
            Log.e("확인", "requestCollectHeartBeat: $it")
        }.launchIn(viewModelScope)
    }
}
