package com.sandy.logisync.presentation.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _heartRate = MutableStateFlow(0)
    val hearRate = _heartRate.asStateFlow()

    fun updateHeartRate(heartRate: Int) {
        _heartRate.value = heartRate
    }
}
