package com.feature.home.model

import androidx.compose.runtime.Stable
import com.core.model.Account
import com.core.model.HeartRate
import com.feature.home.utils.DateUtils

@Stable
data class HomeUiState(
    val date: String = DateUtils.getDate(),
    val account: Account = Account(),
    val heartRateLoading: Boolean = false,
    val heartRate: HeartRate = HeartRate(),
    val pairedDeviceName: String = "",
    val isPairedWatch: Boolean = false,
)
