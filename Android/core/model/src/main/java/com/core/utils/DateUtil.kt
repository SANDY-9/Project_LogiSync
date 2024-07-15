package com.core.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DateUtil {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 ")
    fun getDate(
        year: Int = LocalDateTime.now().year,
        month: Int = LocalDateTime.now().monthValue,
        day: Int = LocalDateTime.now().dayOfMonth,
    ): String {
        val date = LocalDate.of(year, month, day)
        val dateStr = date.format(dateFormatter)
        val dayOfWeekStr = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA)
        return dateStr + dayOfWeekStr
    }

    fun convertTime(dateStr: String): String {
        if (dateStr.isNotBlank()) {
            val time = dateStr.split("-").last()
            val hour = time.split(":").first().toInt()
            val suffix = if (hour >= 12) "오후" else "오전"
            return "$suffix $time"
        }
        else {
            return ""
        }
    }
}
