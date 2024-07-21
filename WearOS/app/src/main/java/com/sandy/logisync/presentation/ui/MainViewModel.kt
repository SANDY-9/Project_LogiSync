package com.sandy.logisync.presentation.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.domain.RequestNormalArrestUseCase
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import com.sandy.logisync.workmanager.HeartRateMeasureWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val requestNormalArrestUseCase: RequestNormalArrestUseCase,
    application: Application,
) : AndroidViewModel(application) {

    private val _initialPairedMobile = MutableStateFlow<Boolean>(false)
    val initialPairedMobile = _initialPairedMobile.asStateFlow()

    private val _measuredHeartRate = MutableStateFlow(
        MeasuredHeartRate(MeasuredAvailability.NONE, null)
    )
    val measuredHeartRate = _measuredHeartRate.asStateFlow()

    private val _isGrantedPermission = MutableStateFlow(false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    init {
        viewModelScope.launch {
            _initialPairedMobile.value = wearableDataStoreRepository.getAccount() != null
        }

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
        val context = getApplication<Application>().applicationContext
        HeartRateMeasureWorker.enqueueWorker(context)
    }

    fun arrest() {
        viewModelScope.launch {
            val account = wearableDataStoreRepository.getAccount()
            account?.let {
                requestNormalArrestUseCase(it.id).catch {
                    Log.e("[REQUEST_ARREST]", "$it : ${it.message}")
                }.collectLatest {
                    Log.i("[REQUEST_ARREST]", "$it")
                }
            }
        }
    }
}
