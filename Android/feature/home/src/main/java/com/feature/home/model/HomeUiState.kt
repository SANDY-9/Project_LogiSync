package com.feature.home.model

import androidx.compose.runtime.Stable
import com.core.model.Account
import com.core.model.HeartRate

@Stable
data class HomeUiState(
    val date: String = "",
    val account: Account = Account(),
    val heartRate: HeartRate = HeartRate(),
    val pairedDeviceName: String = "",
    val isPairedWatch: Boolean = false,
)
