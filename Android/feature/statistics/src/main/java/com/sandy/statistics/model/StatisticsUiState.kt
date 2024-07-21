package com.sandy.statistics.model

import com.core.model.HeartRate
import java.time.LocalDate

data class StatisticsUiState(

    val id: String = "",

    // chart
    val chartType: ChartType = ChartType.DAILY,
    val pickedDate: LocalDate = LocalDate.now(),
    val minBPM: Int = 0,
    val maxBPM: Int = 0,
    val recordItem: List<HeartRate> = emptyList(),
    val chartItem: List<HeartRateChartItem> = emptyList(),
    val isItemEmpty: Boolean = true,

    // DatePicker
    val datePickerVisible: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val selectedStartDateStr: String = "시작 날짜",
    val selectedEndDateStr: String = "마지막 날짜",

    ) {
    enum class ChartType {
        DAILY, RANGE,
    }
}
