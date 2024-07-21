package com.sandy.logisync.model.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    private val dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun convertDate(date: LocalDateTime): String {
        val dateStr = date.format(dateFormatter)
        return dateStr
    }

    fun convertTime(date: LocalDateTime): String {
        val timeStr = date.format(timeFormatter)
        val time = if(timeStr.substring(0,2).toInt() < 12) "오전" else "오후"
        return "$time $timeStr"
    }

}
