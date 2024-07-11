package com.feature.home.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DateUtils {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 ")
    fun getDate(): String {
        val date = LocalDate.now()
        val dateStr = date.format(dateFormatter)
        val dayOfWeekStr = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA)
        return dateStr + dayOfWeekStr
    }
}
