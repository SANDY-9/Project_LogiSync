package com.sandy.logisync.data.network.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object IndexChildCreateUtil {

    private val date = LocalDate.now()
    private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM")
    private val dayFormatter = DateTimeFormatter.ofPattern("dd")
    private val timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
    private val timeSecondsFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun getYearMonthIndexChild(): String {
        return yearMonthFormatter.format(date)
    }

    fun getDayIndexChild(): String {
        return dayFormatter.format(date)
    }

    fun getTimeIndexChild(time: LocalDateTime): String {
        return timeFormatter.format(time)
    }
    fun getTimeSecondsIndexChild(time: LocalDateTime): String {
        return timeSecondsFormatter.format(time)
    }

    fun getDateIndexChild(): String {
        return dateFormatter.format(date)
    }
}
