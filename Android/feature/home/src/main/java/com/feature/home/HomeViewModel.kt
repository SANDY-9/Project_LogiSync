package com.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetLastHeartRateUseCase
import com.core.domain.usecases.network.GetLastMyArrestUseCase
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.domain.usecases.prefs.GetLastPairedDeviceUseCase
import com.core.domain.usecases.wearable.CollectHeartRateUseCase
import com.core.domain.usecases.wearable.GetWearableConnectStateUseCase
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
    private val collectHeartRateUseCase: CollectHeartRateUseCase,
    private val getLastHeartRateUseCase: GetLastHeartRateUseCase,
    private val getLastMyArrestUseCase: GetLastMyArrestUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    internal val stateFlow: StateFlow<HomeUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    private var scope: CoroutineScope? = CoroutineScope(Dispatchers.IO)

    init {
        @Suppress("OPT_IN_USAGE")
        getAccountUseCase().flatMapLatest{ account ->
            account?.let {
                _stateFlow.update { state -> state.copy(account = it) }
                combine(
                    getLastHeartRateUseCase(it.id),
                    getLastMyArrestUseCase(it.id)
                ) { heartRate, arrest ->
                    Log.e("확인", "$heartRate: $arrest", )
                    state.copy(
                        heartRate = heartRate,
                        reportList = arrest,
                        emptyReport = arrest.isEmpty(),
                    )
                }
            }
            ?: flowOf(null)
        }
            .onEach { state ->
                state?.let {
                    _stateFlow.value = it
                }
            }
            .launchIn(viewModelScope)

        combine(
            getLastHeartRateUseCase("nal0256"),
            getLastMyArrestUseCase("nal0256")
        ) { heartRate, arrest ->
            Log.e("확인", "$heartRate: $arrest", )
            state.copy(
                heartRate = heartRate,
                reportList = arrest,
                emptyReport = arrest.isEmpty(),
            )
        }
            .onEach { state ->
                state?.let {
                    _stateFlow.value = it
                }
            }
            .launchIn(viewModelScope)

        monitorWearableConnectState()
    }

    private fun monitorWearableConnectState() {
        combine(
            getWearableConnectStateUseCase(),
            getLastPairedDeviceUseCase(),
        ) { pairedWatch, pairedDevice ->
            _stateFlow.value.copy(
                isPairedWatch = pairedWatch != null,
                pairedDeviceName = pairedDevice?.alias ?: ""
            )
        }.onEach {
            _stateFlow.value = it
        }.launchIn(scope!!)
    }

    fun requestCollectHeartBeat() {
        state.account?.let {
            flow {
                emit(collectHeartRateUseCase(it.id))
            }.catch {
                Log.e("확인", "requestCollectHeartBeat: $it")
            }.launchIn(viewModelScope)
        }
    }

    fun stopWearableConnectMonitoring() {
        scope?.cancel()
        scope = null
    }

    fun restartWearableConnectMonitoring() {
        if(scope == null) {
            scope = CoroutineScope(Dispatchers.IO)
            monitorWearableConnectState()
        }
    }
}
