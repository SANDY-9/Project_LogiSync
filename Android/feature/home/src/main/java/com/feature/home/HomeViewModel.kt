package com.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetLastHeartRateUseCase
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.domain.usecases.prefs.GetLastPairedDeviceUseCase
import com.core.domain.usecases.wearable.CollectHeartRateUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
import com.core.domain.usecases.wearable.LoginWearableUseCase
import com.feature.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWearableConnectStateUseCase: GetWearableConnectStateUseCase,
    private val getLastPairedDeviceUseCase: GetLastPairedDeviceUseCase,
    getAccountUseCase: GetAccountUseCase,
    private val loginWearableUseCase: LoginWearableUseCase,
    private val collectHeartRateUseCase: CollectHeartRateUseCase,
    private val getLastHeartRateUseCase: GetLastHeartRateUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    internal val stateFlow: StateFlow<HomeUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    private var scope: CoroutineScope? = CoroutineScope(Dispatchers.IO)

    init {
        getAccountUseCase().flatMapLatest{ account ->
            account?.let {
                _stateFlow.update { state -> state.copy(account = it) }
                getLastHeartRateUseCase(it.id)
            }
            ?: flowOf(null)
        }
            .onEach { heartRate ->
                _stateFlow.update { state -> state.copy(heartRate = heartRate) }
            }
            .launchIn(viewModelScope)

        monitorWearableConnectState()
    }

    private fun monitorWearableConnectState() {
        combine(
            getWearableConnectStateUseCase(),
            getLastPairedDeviceUseCase(),
        ) { pairedWatch, pairedDevice ->
            Log.e("확인", "$pairedWatch: $pairedDevice", )
            _stateFlow.value.copy(
                isPairedWatch = pairedWatch != null,
                pairedDeviceName = pairedDevice?.alias ?: ""
            )
        }.onEach {
            _stateFlow.value = it
        }.launchIn(scope!!)
    }

    fun requestCollectHeartBeat() {
        flow {
            emit(collectHeartRateUseCase(""))
        }.catch {
            Log.e("확인", "requestCollectHeartBeat: $it")
        }.launchIn(viewModelScope)
    }
}
