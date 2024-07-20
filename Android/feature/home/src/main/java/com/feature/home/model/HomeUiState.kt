package com.feature.home.model

import androidx.compose.runtime.Stable
import com.core.model.Account
import com.core.model.HeartRate
import com.feature.home.utils.DateUtils

@Stable
data class HomeUiState(
    val date: String = DateUtils.getDate(),
    val account: Account? = null,
    val heartRateLoading: Boolean = false,
    val heartRate: HeartRate? = null,
    val pairedDeviceName: String = "",
    val isPairedWatch: Boolean = false,
    val reportList: List<String> = emptyList(),
    val emptyReport: Boolean = true,
)
