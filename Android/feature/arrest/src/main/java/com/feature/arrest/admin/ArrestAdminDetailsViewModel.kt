package com.feature.arrest.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetMyArrestListUseCase
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.model.User
import com.core.utils.DateUtil
import com.feature.arrest.admin.model.ArrestAdminDetailsUiState
import com.feature.arrest.model.ArrestUiState
import com.feature.arrest.utils.filter
import com.feature.arrest.utils.localDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ArrestAdminDetailsViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getMyArrestListUseCase: GetMyArrestListUseCase,
): ViewModel() {

    private val _stateFlow: MutableStateFlow<ArrestAdminDetailsUiState> = MutableStateFlow(ArrestAdminDetailsUiState())
    internal val stateFlow: StateFlow<ArrestAdminDetailsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    internal fun getArrestList(user: User) {
        _stateFlow.value = state.copy(user = user)
        getMyArrestListUseCase(user.id)
            .onStart {
                _stateFlow.value = state.copy(loading = true)
            }
            .onEach { data ->
                delay(300L)
                _stateFlow.update {
                    it.copy(
                        loading = false,
                        arrestList = data,
                        searchedList = data,
                        filteredList = data,
                    )
                }
            }.catch {
                Log.e("[USER_ARREST]", "$it")
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

}
