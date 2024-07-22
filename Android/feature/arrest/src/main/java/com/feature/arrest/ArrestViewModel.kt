package com.feature.arrest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetMyArrestListUseCase
import com.core.utils.DateUtil
import com.feature.arrest.model.ArrestUiState
import com.feature.arrest.utils.filter
import com.feature.arrest.utils.localDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArrestViewModel @Inject constructor(
    private val getMyArrestListUseCase: GetMyArrestListUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<ArrestUiState> = MutableStateFlow(ArrestUiState())
    internal val stateFlow: StateFlow<ArrestUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    internal fun getMyArrestList(id: String) {
        Log.e("확인", "getMyArrestList: $id", )
        getMyArrestListUseCase(id).onEach { data ->
            _stateFlow.update {
                it.copy(
                    arrestList = data,
                    filteredList = data,
                )
            }
        }.catch {
            Log.e("[MY_ARREST]", "$it")
        }.launchIn(viewModelScope)
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
            filteredList = state.arrestList,
        )
    }

    private fun getDangerArrestList() {
        if (state.dangerFilterSelected) return
        _stateFlow.value = state.copy(
            filterType = ArrestUiState.FilterType.FILTER_DANGER,
            allFilterSelected = false,
            dangerFilterSelected = true,
            heartRateFilterSelected = false,
            filteredList = state.arrestList.filter(ArrestUiState.FilterType.FILTER_DANGER)
        )
    }

    private fun getHeartRateArrestList() {
        if (state.heartRateFilterSelected) return
        _stateFlow.value = state.copy(
            filterType = ArrestUiState.FilterType.FILTER_HEART_RATE,
            allFilterSelected = false,
            dangerFilterSelected = false,
            heartRateFilterSelected = true,
            filteredList = state.arrestList.filter(ArrestUiState.FilterType.FILTER_HEART_RATE)
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
        _stateFlow.value = state.copy(
            filteredList = state.arrestList.filter(
                type = state.filterType,
                startDate = startDate,
                endDate = endDate
            )
        )
    }

}
