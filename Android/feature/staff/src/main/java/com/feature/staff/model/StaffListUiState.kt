package com.feature.staff.model

import com.core.model.Staff

internal data class StaffListUiState(

    val loading: Boolean = true,

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
    val pagingFilterVisible: Boolean = false,

    // list
    val staffList: List<Staff> = emptyList(),
    val filteredStaffList: List<Staff> = emptyList(),
    val filteredStaffListCurrentPage: List<Staff> = emptyList(),

    // page
    val paging: Int = 10,
    val currentPage: Int = 1,
    val lastPage: Int = 10,
    val totalPages: List<List<Int>> = (1..lastPage).chunked(5),
    val pageIndex: Int = 0,
    val currentPages: List<Int> = totalPages[pageIndex],
    val isFirstPage: Boolean = true,
    val isEndPage: Boolean = false,
) {
    enum class FilterType {
        WALK, WALK_DISTANCE, HEART_RATE,
        WALK_WARNING, WALK_DISTANCE_WARNING, HEART_RATE_WARNING;
    }
}
