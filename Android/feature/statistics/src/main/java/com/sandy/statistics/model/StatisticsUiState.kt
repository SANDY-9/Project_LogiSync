package com.sandy.statistics.model

import com.core.model.HeartRate
import java.time.LocalDate

data class StatisticsUiState(

    val id: String = "",
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth,
    val minBPM: Int = 0,
    val maxBPM: Int = 0,
    val recordItem: List<HeartRate> = emptyList(),
    val chartItem: List<HeartRateChartItem> = emptyList(),

    // DatePicker
    val datePickerVisible: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val selectedStartDateStr: String = "시작 날짜",
    val selectedEndDateStr: String = "마지막 날짜",

    )
