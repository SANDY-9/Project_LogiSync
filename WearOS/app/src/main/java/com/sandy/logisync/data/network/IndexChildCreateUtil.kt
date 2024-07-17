package com.sandy.logisync.data.network

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object IndexChildCreateUtil {

    private val date = LocalDate.now()
    private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM")
    private val dayFormatter = DateTimeFormatter.ofPattern("dd")
    private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")

    fun getYearMonthIndexChild(): String {
        return yearMonthFormatter.format(date)
    }

    fun getDayIndexChild(): String {
        return dayFormatter.format(date)
    }

    fun getTimeIndexChild(time: LocalDateTime): String {
        return timeFormatter.format(time)
    }
}
