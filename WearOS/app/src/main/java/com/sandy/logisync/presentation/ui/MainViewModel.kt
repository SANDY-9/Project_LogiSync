package com.sandy.logisync.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val healthMeasureRepository: HealthMeasureRepository,
) : ViewModel() {

    private val _measuredHeartRate = MutableStateFlow(
        MeasuredHeartRate(MeasuredAvailability.NONE, null)
    )
    val measuredHeartRate = _measuredHeartRate.asStateFlow()

    private val _isGrantedPermission = MutableStateFlow(false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    init {
        collectHeartRate()
    }

    fun updateGrantedPermission(isGranted: Boolean) {
        _isGrantedPermission.value = isGranted
    }

    fun collectHeartRate() {
        healthMeasureRepository.getMeasuredHeartRate().onEach {
            _measuredHeartRate.value = it
        }.launchIn(viewModelScope)
    }
}
