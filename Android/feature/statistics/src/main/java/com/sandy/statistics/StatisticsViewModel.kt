package com.sandy.statistics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.usecases.network.GetDailyHeartRateListUseCase
import com.core.domain.usecases.network.GetPeriodHearRateListUseCase
import com.core.domain.usecases.prefs.GetAccountUseCase
import com.core.utils.DateUtil
import com.sandy.statistics.model.StatisticsUiState
import com.sandy.statistics.utils.lastNotNullIndex
import com.sandy.statistics.utils.localDate
import com.sandy.statistics.utils.maxBPM
import com.sandy.statistics.utils.minBPM
import com.sandy.statistics.utils.selectRecordItem
import com.sandy.statistics.utils.toDailyChartItem
import com.sandy.statistics.utils.toPeriodChartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    getAccountUseCase: GetAccountUseCase,
    private val getDailyHeartRateListUseCase: GetDailyHeartRateListUseCase,
    private val getPeriodHearRateListUseCase: GetPeriodHearRateListUseCase,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<StatisticsUiState> = MutableStateFlow(StatisticsUiState())
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
        }.catch {
        }.timeout(10.seconds).launchIn(viewModelScope)
    }

    private fun requestDailyHeartRates(
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
            }.timeout(10.seconds).collectLatest { data ->
                _stateFlow.update {
                    val chartItem = data.toDailyChartItem()
                    val selectPosition = chartItem.lastNotNullIndex()
                    val selectRecordItem = if(selectPosition == null && data.isNotEmpty()) data else data.selectRecordItem(selectPosition)
                    it.copy(
                        chartType = StatisticsUiState.ChartType.DAILY,
                        pickedDate = LocalDate.of(year, month, day),
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

    internal fun selectItem(position: Int) {
        state.run {
            val selectRecordItem = if(chartType == StatisticsUiState.ChartType.DAILY) {
                recordItem.selectRecordItem(position)
            }
            else {
                val selectDate = chartItem[position].date
                recordItem.selectRecordItem(selectDate)
            }
            _stateFlow.value = copy(
                selectPosition = position,
                minBPM = state.chartItem[position].minBpm,
                maxBPM = state.chartItem[position].maxBpm,
                selectRecordItem = selectRecordItem,
                isSelectItemEmpty = selectRecordItem.isEmpty(),
            )
        }
    }

    internal fun getPrevDateChart() {
        _stateFlow.update {
            val prevDate = it.pickedDate.minusDays(1)
            requestDailyHeartRates(prevDate.year, prevDate.monthValue, prevDate.dayOfMonth)
            it.copy(pickedDate = prevDate)
        }
    }

    internal fun getNextDateChart() {
        _stateFlow.update {
            val nextDate = it.pickedDate.plusDays(1)
            requestDailyHeartRates(nextDate.year, nextDate.monthValue, nextDate.dayOfMonth)
            it.copy(pickedDate = it.pickedDate.plusDays(1))
        }
    }

    internal fun resetChart() {
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
        val startDate = state.selectedStartDate
        val endDate = state.selectedEndDate
        if (startDate == endDate || startDate == null || endDate == null) {
            val localDate = startDate.localDate() ?: endDate.localDate()
            localDate?.let { date ->
                requestDailyHeartRates(date.year, date.monthValue, date.dayOfMonth)
            }
        } else {
            requestHeartRateByPeriod(
                startDate = startDate.localDate(),
                endDate = endDate.localDate()
            )
        }
    }

    private fun requestHeartRateByPeriod(startDate: LocalDate?, endDate: LocalDate?) {
        if(startDate == null || endDate == null) return
        viewModelScope.launch {
            getPeriodHearRateListUseCase(state.id, startDate, endDate)
                .timeout(10.seconds)
                .catch {
                    Log.e("[HEART_RATE_RECORD_PERIOD]", "requestHeartRateByPeriod: $it")
                }.collectLatest { data ->
                    _stateFlow.update {
                        val chartItem = data.toPeriodChartItem(startDate, endDate)
                        val selectPosition = chartItem.lastNotNullIndex()
                        val selectDate =
                            if (selectPosition != null) chartItem[selectPosition].date else null
                        val selectRecordItem = data.selectRecordItem(selectDate)
                        it.copy(
                            chartType = StatisticsUiState.ChartType.PERIOD,
                            minBPM = selectPosition.minBPM(chartItem),
                            maxBPM = selectPosition.maxBPM(chartItem),
                            recordItem = data,
                            selectRecordItem = selectRecordItem,
                            chartItem = chartItem,
                            selectPosition = selectPosition,
                            isSelectItemEmpty = selectRecordItem.isEmpty(),
                            selectDateTitle = "${DateUtil.convertDate(startDate)} - ${
                                DateUtil.convertDate(
                                    endDate
                                )
                            }",
                        )
                    }
                }
        }
    }

}
