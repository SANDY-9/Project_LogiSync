package com.feature.arrest.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetArrestListUseCase
import com.core.utils.DateUtil
import com.feature.arrest.admin.model.ArrestAdminUiState
import com.feature.arrest.model.ArrestUiState
import com.feature.arrest.utils.filter
import com.feature.arrest.utils.localDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArrestAdminViewModel @Inject constructor(
    getArrestListUseCase: GetArrestListUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<ArrestAdminUiState> = MutableStateFlow(ArrestAdminUiState())
    internal val stateFlow: StateFlow<ArrestAdminUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getArrestListUseCase().onStart {
            _stateFlow.value = state.copy(loading = true)
        }.onEach { data ->
            _stateFlow.update {
                it.copy(
                    arrestList = data,
                    searchedList = data,
                    filteredList = data,
                    loading = false,
                )
            }
        }.catch {
            Log.e("[ALL_ARREST]", "$it")
            delay(500)
            _stateFlow.value = state.copy(loading = false)
        }.launchIn(viewModelScope)
    }

    internal fun refreshArrestList() {
        viewModelScope.launch {
            _stateFlow.value = state.copy(loading = true)
            delay(500)
            _stateFlow.value = state.copy(
                loading = false,
                filterType = ArrestUiState.FilterType.FILTER_ALL,
                allFilterSelected = true,
                dangerFilterSelected = false,
                heartRateFilterSelected = false,
                searchedList = state.arrestList,
                filteredList = state.arrestList,
            )
        }
    }

    internal fun filterArrestList(type: ArrestUiState.FilterType) {
        when(type) {
            ArrestUiState.FilterType.FILTER_ALL -> getAllArrestList()
            ArrestUiState.FilterType.FILTER_DANGER -> getDangerArrestList()
            ArrestUiState.FilterType.FILTER_HEART_RATE -> getHeartRateArrestList()
        }
    }

    private fun getAllArrestList() {
        if (state.allFilterSelected) return
        _stateFlow.value = state.copy(
            filterType = ArrestUiState.FilterType.FILTER_ALL,
            allFilterSelected = true,
            dangerFilterSelected = false,
            heartRateFilterSelected = false,
            filteredList = state.searchedList,
        )
    }

    private fun getDangerArrestList() {
        if (state.dangerFilterSelected) return
        _stateFlow.value = state.copy(
            filterType = ArrestUiState.FilterType.FILTER_DANGER,
            allFilterSelected = false,
            dangerFilterSelected = true,
            heartRateFilterSelected = false,
            filteredList = state.searchedList.filter(ArrestUiState.FilterType.FILTER_DANGER)
        )
    }

    private fun getHeartRateArrestList() {
        if (state.heartRateFilterSelected) return
        _stateFlow.value = state.copy(
            filterType = ArrestUiState.FilterType.FILTER_HEART_RATE,
            allFilterSelected = false,
            dangerFilterSelected = false,
            heartRateFilterSelected = true,
            filteredList = state.searchedList.filter(ArrestUiState.FilterType.FILTER_HEART_RATE)
        )
    }

    internal fun setDatePickerVisible() {
        _stateFlow.value = state.copy(
            datePickerVisible = !state.datePickerVisible
        )
    }

    internal fun selectedStartDate(date: Long?) {
        _stateFlow.update {
            val str = date?.let { date -> DateUtil.convertDate(date) } ?: "시작 날짜"
            it.copy(
                selectedStartDate = date,
                selectedStartDateStr = str,
            )
        }
    }

    internal fun selectedEndDate(date: Long?) {
        _stateFlow.update {
            val str = date?.let { date -> DateUtil.convertDate(date) } ?: "마지막 날짜"
            it.copy(
                selectedEndDate = date,
                selectedEndDateStr = str,
            )
        }
    }

    internal fun completeDatePicker() {
        val startDate = state.selectedStartDate.localDate() ?: return
        val endDate = state.selectedEndDate.localDate() ?: return
        filterByDate(startDate, endDate)
    }

    private fun filterByDate(startDate: LocalDate, endDate: LocalDate) {
        viewModelScope.launch {
            _stateFlow.value = state.copy(loading = true)
            delay(500)
            val searchedList = state.arrestList.filter(
                type = ArrestUiState.FilterType.FILTER_ALL,
                startDate = startDate,
                endDate = endDate
            )
            _stateFlow.value = state.copy(
                loading = false,
                searchedList = searchedList,
                filterType = ArrestUiState.FilterType.FILTER_ALL,
                allFilterSelected = true,
                dangerFilterSelected = false,
                heartRateFilterSelected = false,
                filteredList = searchedList,
            )
        }
    }

    internal fun inputQuery(query: String) {
        _stateFlow.value = _stateFlow.value.copy(query = query)
    }

    internal fun clearQuery() {
        _stateFlow.value = _stateFlow.value.copy(query = "")
    }

    internal fun requestSearch() {
        if(state.query.isBlank()) return
        viewModelScope.launch {
            _stateFlow.value = state.copy(loading = true)
            delay(500L)
            val query = state.query.trim()
            val searchList = state.arrestList.filter(query)
            _stateFlow.value = state.copy(
                searchedList = searchList,
                filteredList = searchList,
                allFilterSelected = true,
                dangerFilterSelected = false,
                loading = false,
            )
        }
    }

}
