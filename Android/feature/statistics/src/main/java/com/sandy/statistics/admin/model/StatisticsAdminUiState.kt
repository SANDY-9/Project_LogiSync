package com.sandy.statistics.admin.model

import com.core.model.HeartRate
import com.core.model.User
import com.sandy.statistics.model.HeartRateChartItem
import com.sandy.statistics.model.StatisticsUiState.ChartType
import java.time.LocalDate

data class StatisticsAdminUiState(

    val user: User? = null,
    val error: Throwable? = null,

    // chart
    val chartType: ChartType = ChartType.DAILY,
    val pickedDate: LocalDate = LocalDate.now(),
    val minBPM: Int? = null,
    val maxBPM: Int? = null,
    val recordItem: List<HeartRate> = emptyList(),
    val selectRecordItem: List<HeartRate> = emptyList(),
    val chartItem: List<HeartRateChartItem> = emptyList(),
    val isSelectItemEmpty: Boolean = true,
    val selectPosition: Int? = null,

    // DatePicker
    val datePickerVisible: Boolean = false,
    val selectedStartDate: Long? = null,
    val selectedEndDate: Long? = null,
    val selectedStartDateStr: String = "시작 날짜",
    val selectedEndDateStr: String = "마지막 날짜",
    val selectDateTitle: String = "",

)
