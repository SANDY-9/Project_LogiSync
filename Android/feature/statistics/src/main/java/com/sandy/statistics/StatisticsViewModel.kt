package com.sandy.statistics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetDailyHeartRateListUseCase
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.utils.DateUtil
import com.sandy.statistics.model.StatisticsUiState
import com.sandy.statistics.utils.lastNotNullIndex
import com.sandy.statistics.utils.maxBPM
import com.sandy.statistics.utils.minBPM
import com.sandy.statistics.utils.selectRecordItem
import com.sandy.statistics.utils.toChartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    getAccountUseCase: GetAccountUseCase,
    private val getDailyHeartRateListUseCase: GetDailyHeartRateListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<StatisticsUiState> =
        MutableStateFlow(StatisticsUiState())
    internal val stateFlow: StateFlow<StatisticsUiState> = _stateFlow.asStateFlow()
    private val state get() = stateFlow.value

    init {
        getAccountUseCase().onEach { account ->
            account?.let {
                val id = it.id
                LocalDate.now().run {
                    _stateFlow.update { state ->
                        state.copy(
                            id = id,
                            pickedDate = this
                        )
                    }
                    requestDailyHeartRates(year, monthValue, dayOfMonth, id)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun requestDailyHeartRates(
        year: Int,
        month: Int,
        day: Int,
        id: String = state.id
    ) {
        viewModelScope.launch {
            getDailyHeartRateListUseCase(
                id = id,
                year = year,
                month = month,
                day = day
            ).catch {
                Log.e("[HEART_RATE_RECORD_DAILY]", "requestDailyHeartRates: $it")
            }.collectLatest { data ->
                Log.e("확인", "requestDailyHeartRates: $data", )
                _stateFlow.update {
                    val chartItem = data.toChartItem()
                    val selectPosition = chartItem.lastNotNullIndex()
                    val selectRecordItem = if(selectPosition == null && data.isNotEmpty()) data else data.selectRecordItem(selectPosition)
                    it.copy(
                        chartType = StatisticsUiState.ChartType.DAILY,
                        minBPM = selectPosition.minBPM(chartItem),
                        maxBPM = selectPosition.maxBPM(chartItem),
                        recordItem = data,
                        selectRecordItem = selectRecordItem,
                        chartItem = chartItem,
                        selectPosition = selectPosition,
                        isSelectItemEmpty = selectRecordItem.isEmpty(),
                    )
                }
            }
        }
    }


    fun selectItem(position: Int) {
        val selectRecordItem = state.recordItem.selectRecordItem(position)
        _stateFlow.value = state.copy(
            selectPosition = position,
            minBPM = state.chartItem[position].minBpm,
            maxBPM = state.chartItem[position].maxBpm,
            selectRecordItem = selectRecordItem,
            isSelectItemEmpty = selectRecordItem.isEmpty(),
        )
    }

    fun getPrevDateChart() {
        _stateFlow.update {
            val prevDate = it.pickedDate.minusDays(1)
            requestDailyHeartRates(prevDate.year, prevDate.monthValue, prevDate.dayOfMonth)
            it.copy(pickedDate = prevDate)
        }
    }

    fun getNextDateChart() {
        _stateFlow.update {
            val nextDate = it.pickedDate.plusDays(1)
            requestDailyHeartRates(nextDate.year, nextDate.monthValue, nextDate.dayOfMonth)
            it.copy(pickedDate = it.pickedDate.plusDays(1))
        }
    }

    fun resetChart() {
        val pickedDate = LocalDate.now()
        _stateFlow.update {
            it.copy(
                chartType = StatisticsUiState.ChartType.DAILY,
                pickedDate = pickedDate,
            )
        }.also {
            requestDailyHeartRates(pickedDate.year, pickedDate.monthValue, pickedDate.dayOfMonth)
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
