package com.sandy.logisync.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.data.location.LocationRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.Arrest
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val healthMeasureRepository: HealthMeasureRepository,
    private val networkRepository: NetworkRepository,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    private val _initialPairedMobile = MutableStateFlow<Boolean>(false)
    val initialPairedMobile = _initialPairedMobile.asStateFlow()

    private val _measuredHeartRate = MutableStateFlow(
        MeasuredHeartRate(MeasuredAvailability.NONE, null)
    )
    val measuredHeartRate = _measuredHeartRate.asStateFlow()

    private val _isGrantedPermission = MutableStateFlow(false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    init {
        wearableDataStoreRepository.getAccount().onEach {
            _initialPairedMobile.value = it != null
        }.launchIn(viewModelScope)
        // 심박수
        wearableDataStoreRepository.getLastHeartRate().shareIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
        ).onEach { lastHeartRate ->
            Log.e("확인", "$lastHeartRate: ")
            if (lastHeartRate == null) {
                collectHeartRate()
            }
            else {
                _measuredHeartRate.value = MeasuredHeartRate(
                    availability = MeasuredAvailability.AVAILABLE,
                    heartRate = lastHeartRate
                )
            }
        }.launchIn(viewModelScope)
    }

    fun updateGrantedPermission(isGranted: Boolean) {
        _isGrantedPermission.value = isGranted
    }

    fun collectHeartRate() {
        healthMeasureRepository.getMeasuredHeartRate().onEach {
            _measuredHeartRate.value = it
            networkRequest(it.heartRate)
        }.launchIn(viewModelScope)
    }

    private fun networkRequest(heartRate: HeartRate?) {
        heartRate?.let {
            networkRepository.updateHeartRate(it.bpm, it.time).onEach {
                Log.i("[NETWORK]", "networkRequest: OK")
            }.catch {
                Log.e("[NETWORK]", "networkRequest: FAILED")
            }.launchIn(viewModelScope)
        }
    }

    fun arrest() {
        wearableDataStoreRepository.getAccount().onEach { account ->
            account?.let { account ->
                val token = "fPJzCSEXQNWzZYXGGx_dk1:APA91bFI6IwtGwE19SLN5SBbJrPOXyqoZkUlWdF3jhiaNWbi1GXrIoC7H-4H3qh4uGYOtZftLZj3yJbPY0uEii7itVsWnn7T7oBDr237_VxnYl6xfxbr-dzPGi5cTOd-C--naeBpY_kp"
                locationRepository.getLastLocation().collectLatest { location ->
                    networkRepository.updateArrest(
                        id = account.id,
                        arrestType = Arrest.ArrestType.NORMAL,
                        location = location,
                        token = token
                    ).collectLatest {
                        networkRepository.notifyArrest(account.id, token).collect()
                    }
                }
            }
        }.catch {
            Log.e("[NETWORK]", "networkRequest: FAILED")
            Log.e("[NETWORK]", "networkRequest: $it")
        }.launchIn(viewModelScope)
    }
}
