package com.sandy.logisync.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    healthMeasureRepository: HealthMeasureRepository,
) : ViewModel() {

    val measuredHeartRate = healthMeasureRepository.getMeasuredHeartRate()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = MeasuredHeartRate(MeasuredAvailability.LOADING, null)
        )

    private val _isGrantedPermission = MutableStateFlow(false)
    val isGrantedPermission = _isGrantedPermission.asStateFlow()

    fun updateGrantedPermission(isGranted: Boolean) {
        _isGrantedPermission.value = isGranted
    }
}
