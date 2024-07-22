package com.feature.admin.details.model

import com.core.model.Arrest
import com.core.model.HeartRate
import com.core.model.User

data class UserDetailsUiState(
    val user: User? = null,
    val lastReportList: List<Arrest> = emptyList(),
    val lastHeartRateList: List<HeartRate> = emptyList(),
)
