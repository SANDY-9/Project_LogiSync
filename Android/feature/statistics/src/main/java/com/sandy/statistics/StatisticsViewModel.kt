package com.sandy.statistics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.domain.usecases.statistics.GetDailyHeartRateListUseCase
import com.core.model.HeartRate
import com.core.utils.DateUtil
import com.sandy.statistics.model.HeartRateChartItem
import com.sandy.statistics.model.StatisticsUiState
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
class StatisticsViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getDailyHeartRateListUseCase: GetDailyHeartRateListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<StatisticsUiState> =
        MutableStateFlow(StatisticsUiState())
    internal val stateFlow: StateFlow<StatisticsUiState> = _stateFlow.asStateFlow()

    init {
        getAccountUseCase().onEach { account ->
            val id = account.id
            LocalDate.now().run {
                _stateFlow.update { state ->
                    state.copy(
                        id = id,
                        pickedDate = this
                    )
                }
                requestDailyHeartRates(year, monthValue, dayOfMonth, id)
            }
        }.launchIn(viewModelScope)
    }

    fun requestDailyHeartRates(
        year: Int,
        month: Int,
        day: Int,
        id: String = stateFlow.value.id
    ) {
        getDailyHeartRateListUseCase(
            id = id,
            year = year,
            month = month,
            day = day
        ).onEach { data ->
            val bpm = data.map { it.bpm }
            _stateFlow.update {
                it.copy(
                    minBPM = bpm.min(),
                    maxBPM = bpm.max(),
                    recordItem = data,
                    chartItem = data.toChartItem(),
                )
            }
        }.catch {
            Log.e("확인", "requestDailyHeartRates: $it")
        }.launchIn(viewModelScope)
    }

    private fun List<HeartRate>.toChartItem(
        minHour: Int = 8,
        maxHour: Int = 19
    ): List<HeartRateChartItem> {
        val groupedByHour = groupBy { it.date.substring(11, 13).toInt() }
        val heartRateChartItems = (minHour..maxHour).map { hour ->
            groupedByHour[hour]?.run {
                val minBpm = minOf { it.bpm }
                val maxBpm = maxOf { it.bpm }
                HeartRateChartItem(hour, minBpm, maxBpm)
            } ?: HeartRateChartItem(hour, null, null)
        }.sortedBy { it.hour }
        return heartRateChartItems
    }

    fun getPrevDateChart() {
        _stateFlow.update {
            it.copy(
                pickedDate = it.pickedDate.minusDays(1),
            )
        }
    }

    fun getNextDateChart() {
        _stateFlow.update {
            it.copy(
                pickedDate = it.pickedDate.plusDays(1),
            )
        }
    }

    fun resetChart() {
        _stateFlow.update {
            it.copy(
                chartType = StatisticsUiState.ChartType.DAILY,
                pickedDate = LocalDate.now(),
                selectedStartDate = null,
                selectedEndDate = null,
            )
        }
    }

    fun setDatePickerVisible() {
        _stateFlow.update {
            it.copy(
                datePickerVisible = !it.datePickerVisible
            )
        }
    }

    fun selectedStartDate(date: Long?) {
        _stateFlow.update {
            val str = date?.let { date -> DateUtil.convertDate(date) } ?: "시작 날짜"
            it.copy(
                selectedStartDate = date,
                selectedStartDateStr = str,
            )
        }
    }

    fun selectedEndDate(date: Long?) {
        _stateFlow.update {
            val str = date?.let { date -> DateUtil.convertDate(date) } ?: "마지막 날짜"
            it.copy(
                selectedEndDate = date,
                selectedEndDateStr = str,
            )
        }
    }

    fun requestHeartRates() {
        _stateFlow.update {
            it.copy(
                chartType = StatisticsUiState.ChartType.RANGE,
            )
        }
    }
}
