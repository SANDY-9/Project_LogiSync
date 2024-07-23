package com.feature.arrest.admin.model

import com.core.model.Arrest
import com.core.model.User
import com.feature.arrest.model.ArrestUiState
import java.time.LocalDate

internal data class ArrestAdminDetailsUiState(

    val loading: Boolean = false,
    val user: User? = null,

    // data
    val arrestList: Map<LocalDate, List<Arrest>> = emptyMap(),
    val searchedList: Map<LocalDate, List<Arrest>> = emptyMap(),
    val filteredList: Map<LocalDate, List<Arrest>> = emptyMap(),
    val allFilterSelected: Boolean = true,
    val dangerFilterSelected: Boolean = false,
    val heartRateFilterSelected: Boolean = false,

    // date picker
    val datePickerVisible: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val selectedStartDateStr: String = "시작 날짜",
    val selectedEndDateStr: String = "마지막 날짜",
    val selectDateTitle: String = "",

    // filter
    val filterType: ArrestUiState.FilterType = ArrestUiState.FilterType.FILTER_ALL,

)
