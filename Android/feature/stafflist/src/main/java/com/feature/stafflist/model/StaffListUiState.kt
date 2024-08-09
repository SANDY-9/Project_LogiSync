package com.feature.stafflist.model

import com.core.model.Staff

data class StaffListUiState(

    // search
    val query: String = "",

    // filter
    val filterExpand: Boolean = false,
    val walkSortSelect: Boolean = false,
    val walkDistanceSortSelect: Boolean = false,
    val heartRateSortSelect: Boolean = false,
    val walkFilterSelect: Boolean = false,
    val walkDistanceFilterSelect: Boolean = false,
    val heartRateFilterSelect: Boolean = false,

    // list
    val staffList: List<Staff> = emptyList(),

    // page
    val selectPage: Int = 1,
    val staffListPaging: List<Staff> = emptyList(),
    val currentPages: List<Int> = listOf(1,2,3,4,5),
)
