package com.feature.admin.details.model

import com.core.model.Account
import com.core.model.Arrest
import com.core.model.HeartRate
import com.core.model.User

data class UserDetailsUiState(

    val loading: Boolean = false,
    val error: Boolean? = null,

    val user: User? = null,
    val account: Account? = null,
    val lastReportList: List<Arrest> = emptyList(),
    val lastHeartRateList: List<HeartRate> = emptyList(),

    // 임계점 변경
    val changeBottomSheetVisible: Boolean = false,
    val editMin: String = "",
    val editMax: String = "",
    val enableEdit: Boolean = false,

)
