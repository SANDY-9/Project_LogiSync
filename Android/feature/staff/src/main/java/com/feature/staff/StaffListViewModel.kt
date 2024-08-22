package com.feature.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetStaffUseCase
import com.feature.staff.model.StaffListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class StaffListViewModel @Inject constructor(
    private val getStaffUseCase: GetStaffUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<StaffListUiState> = MutableStateFlow(StaffListUiState())
    internal val stateFlow: StateFlow<StaffListUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getStaffUseCase()
            .onStart {
                _stateFlow.value = state.copy(
                    loading = true,
                )
            }.onEach { data ->
                delay(500)
                var lastPage = data.size / state.paging
                if(lastPage == 0) lastPage = 1
                val totalPages = (1..lastPage).chunked(5)
                _stateFlow.value = state.copy(
                    staffList = data,
                    filteredStaffList = data,
                    filteredStaffListCurrentPage = data.subList(0, state.paging),
                    lastPage = lastPage,
                    totalPages = totalPages,
                    currentPages = totalPages[0],
                    loading = false,
                )
            }.catch {
                _stateFlow.value = state.copy(loading = false,)
            }.launchIn(viewModelScope)
    }

    internal fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    internal fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    internal fun requestSearch() {
        val currentFilteredData = state.staffList
        val filteredData = currentFilteredData.let { list ->
            if(state.walkSortSelect) list.sortedByDescending { it.walk } else list
        }.let { list ->
            if(state.walkDistanceSortSelect) list.sortedBy { it.km } else list
        }.let { list ->
            if(state.heartRateSortSelect) list.sortedByDescending { it.bpm } else list
        }.let { list ->
            if(state.walkFilterSelect) list.filter { it.walk < 2000 } else list
        }.let { list ->
            if(state.walkDistanceFilterSelect) list.filter { it.km > 5.0 } else list
        }.let { list ->
            if(state.heartRateFilterSelect) list.filter { it.bpm > 100 || it.bpm < 60 } else list
        }.let { list ->
            list.filter { it.name.contains(state.query) }
        }
        var lastPage = filteredData.size / state.paging
        if(lastPage == 0) lastPage = 1
        val endPage = if(state.paging > filteredData.size) filteredData.size else state.paging
        val totalPages = (1..lastPage).chunked(5)
        _stateFlow.value = state.copy(
            filterExpand = false,
            filteredStaffList = filteredData,
            filteredStaffListCurrentPage = filteredData.subList(0, endPage),
            currentPage = 1,
            lastPage = lastPage,
            totalPages = totalPages,
            pageIndex = 0,
            currentPages = totalPages[0],
            isFirstPage = true,
            isEndPage = totalPages[0].size == 1,
        )
    }

    internal fun expandFilter() {
        _stateFlow.value = state.copy(
            filterExpand = !state.filterExpand
        )
    }

    internal fun selectFilter(filterType: StaffListUiState.FilterType) {
        _stateFlow.value = when(filterType) {
            StaffListUiState.FilterType.WALK -> state.copy(
                walkSortSelect = !state.walkSortSelect,
                walkDistanceSortSelect = false,
                heartRateSortSelect = false,
            )
            StaffListUiState.FilterType.WALK_DISTANCE -> state.copy(
                walkDistanceSortSelect = !state.walkDistanceSortSelect,
                walkSortSelect = false,
                heartRateSortSelect = false,
            )
            StaffListUiState.FilterType.HEART_RATE -> state.copy(
                heartRateSortSelect = !state.heartRateSortSelect,
                walkSortSelect = false,
                walkDistanceSortSelect = false,
            )
            StaffListUiState.FilterType.WALK_WARNING -> state.copy(
                walkFilterSelect = !state.walkFilterSelect
            )
            StaffListUiState.FilterType.WALK_DISTANCE_WARNING -> state.copy(
                walkDistanceFilterSelect = !state.walkDistanceFilterSelect
            )
            StaffListUiState.FilterType.HEART_RATE_WARNING -> state.copy(
                heartRateFilterSelect = !state.heartRateFilterSelect
            )
        }
    }

    internal fun initFilter() {
        _stateFlow.value = state.copy(
            query = "",
            walkSortSelect = false,
            walkDistanceSortSelect = false,
            heartRateSortSelect = false,
            walkFilterSelect = false,
            walkDistanceFilterSelect = false,
            heartRateFilterSelect = false,
        )
    }

    internal fun applyFilter() {
        val currentFilteredData = state.staffList
        val filteredData = currentFilteredData.let { list ->
            if(state.walkSortSelect) list.sortedByDescending { it.walk } else list
        }.let { list ->
            if(state.walkDistanceSortSelect) list.sortedBy { it.km } else list
        }.let { list ->
            if(state.heartRateSortSelect) list.sortedByDescending { it.bpm } else list
        }.let { list ->
            if(state.walkFilterSelect) list.filter { it.walk < 2000 } else list
        }.let { list ->
            if(state.walkDistanceFilterSelect) list.filter { it.km > 5.0 } else list
        }.let { list ->
            if(state.heartRateFilterSelect) list.filter { it.bpm > 100 || it.bpm < 60 } else list
        }
        var lastPage = filteredData.size / state.paging
        if(lastPage == 0) lastPage = 1
        val endPage = if(state.paging > filteredData.size) filteredData.size else state.paging
        val totalPages = (1..lastPage).chunked(5)
        _stateFlow.value = state.copy(
            filterExpand = false,
            filteredStaffList = filteredData,
            filteredStaffListCurrentPage = filteredData.subList(0, endPage),
            currentPage = 1,
            lastPage = lastPage,
            totalPages = totalPages,
            pageIndex = 0,
            currentPages = totalPages[0],
            isFirstPage = true,
            isEndPage = totalPages[0].size == 1,
        )
    }

    internal fun changePagingFilter() {
        _stateFlow.value = state.copy(
            pagingFilterVisible = true,
        )
    }

    internal fun selectPaging(paging: Int) {
        var lastPage = state.filteredStaffList.size / paging
        if(lastPage == 0) lastPage = 1
        val totalPages = (1..lastPage).chunked(5)
        _stateFlow.value = state.copy(
            paging = paging,
            pagingFilterVisible = false,
            lastPage = lastPage,
            totalPages = totalPages,
            pageIndex = 0,
            currentPages = totalPages[0],
            filteredStaffListCurrentPage = state.filteredStaffList.subList(0, paging),
            isFirstPage = true,
            isEndPage = false,
            currentPage = 1,
        )
    }

    internal fun selectPage(page: Int) {
        val startPage = (state.paging * page)
        var endPage = state.paging * (page + 1)
        if(endPage > state.filteredStaffList.size) {
            endPage = state.filteredStaffList.size
        }
        _stateFlow.value = state.copy(
            currentPage = page,
            filteredStaffListCurrentPage = state.filteredStaffList.subList(startPage, endPage),
            isFirstPage = page == 1,
            isEndPage = page == state.lastPage,
        )
    }

    internal fun prevPage() {
        val page = state.currentPage - 1
        val startPage = (state.paging * page)
        val endPage = state.paging * (page + 1)
        val isValidPage = page in state.currentPages
        val pageIndex = if(isValidPage) state.pageIndex else state.pageIndex - 1
        val currentPages = state.totalPages[pageIndex]
        _stateFlow.value = state.copy(
            currentPage = page,
            filteredStaffListCurrentPage = state.filteredStaffList.subList(startPage, endPage),
            isFirstPage = page == 1,
            isEndPage = page == state.lastPage,
            pageIndex = pageIndex,
            currentPages = currentPages
        )
    }

    internal fun firstPage() {
        _stateFlow.value = state.copy(
            currentPage = 1,
            filteredStaffListCurrentPage = state.filteredStaffList.subList(0, state.paging),
            isFirstPage = true,
            isEndPage = state.totalPages[0].size == 1,
            pageIndex = 0,
            currentPages = state.totalPages[0],
        )
    }

    internal fun nextPage() {
        val page = state.currentPage + 1
        val startPage = (state.paging * page)
        var endPage = state.paging * (page + 1)
        if(endPage > state.filteredStaffList.size) {
            endPage = state.filteredStaffList.size
        }
        val isValidPage = page in state.currentPages
        val pageIndex = if(isValidPage) state.pageIndex else state.pageIndex + 1
        val currentPages = state.totalPages[pageIndex]
        _stateFlow.value = state.copy(
            currentPage = page,
            filteredStaffListCurrentPage = state.filteredStaffList.subList(startPage, endPage),
            isEndPage = page == state.lastPage,
            isFirstPage = page == 1,
            pageIndex = pageIndex,
            currentPages = currentPages
        )
    }

    internal fun endPage() {
        val startPage = (state.paging * state.lastPage) + 1
        val pageIndex = state.totalPages.size - 1
        _stateFlow.value = state.copy(
            currentPage = state.lastPage,
            filteredStaffListCurrentPage = state.filteredStaffList.subList(startPage, state.filteredStaffList.size),
            isFirstPage = false,
            isEndPage = true,
            pageIndex = pageIndex,
            currentPages = state.totalPages[pageIndex],
        )
    }

}
