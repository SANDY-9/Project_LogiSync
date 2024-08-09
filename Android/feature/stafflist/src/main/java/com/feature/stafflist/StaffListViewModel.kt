package com.feature.stafflist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.staff.GetStaffUseCase
import com.feature.stafflist.model.StaffListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StaffListViewModel @Inject constructor(
    private val getStaffUseCase: GetStaffUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<StaffListUiState> = MutableStateFlow(StaffListUiState())
    internal val stateFlow: StateFlow<StaffListUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getStaffUseCase().onEach {
           _stateFlow.value = state.copy(
               staffList = it,
               staffListPaging = it.subList(0, 10),
           )
        }.launchIn(viewModelScope)
    }

    internal fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    internal fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    internal fun requestSearch() {
        // requestSearch
    }

    internal fun expandFilter() {
        _stateFlow.value = state.copy(
            filterExpand = !state.filterExpand
        )
    }

    internal fun selectPage(page: Int) {
        val startPage = 10 * page + 1
        val endPage = 10 * (page + 1)
        _stateFlow.value = state.copy(
            selectPage = page,
            staffListPaging = state.staffList.subList(startPage, endPage),
        )
    }

    internal fun prevPage() {
        val prevPage = state.currentPages.map {
            it - 5
        }
        _stateFlow.value = state.copy(
            currentPages = prevPage,
        )
    }

    internal fun firstPage() {
        _stateFlow.value = state.copy(
            currentPages = listOf(1,2,3,4,5),
            staffListPaging = state.staffList.subList(0, 10),
        )
    }

    internal fun nextPage() {
        val nextPage = state.currentPages.map {
            it + 5
        }
        _stateFlow.value = state.copy(
            currentPages = nextPage,
        )
    }

    internal fun endPage() {
        val endPage = state.staffList.size / 10
        _stateFlow.value = state.copy(
            currentPages = listOf(1,2,3,4,5),
            staffListPaging = state.staffList.subList(0, 10),
        )
    }

}
