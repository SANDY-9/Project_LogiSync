package com.core.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

object DateUtil {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 ")
    fun convertFullDate(date: LocalDate): String {
        val set = LocalDate.of(date.year, date.month, date.dayOfMonth)
        val dateStr = set.format(dateFormatter)
        val dayOfWeekStr = set.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREA)
        return dateStr + dayOfWeekStr
    }

    fun convertTime(dateStr: String): String {
        if (dateStr.isNotBlank()) {
            val split = dateStr.split("-")
            val hour = split.last().split(":").first().toInt()
            val suffix = if (hour >= 12) "오후" else "오전"
            return "${split.first()}년 ${split[1]}월 ${split[2]}일 $suffix ${split.last()}"
        }
        else {
            return ""
        }
    }

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.KOREA)
    fun convertDate(timeInMillis: Long): String {
        val calender = Calendar.getInstance()
        calender.timeInMillis = timeInMillis
        return dateFormat.format(calender.timeInMillis)
    }

    private val dateTimeDateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private val dateTimeTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    fun convertDateTime(dateTime: LocalDateTime): String {
        val date = dateTime.format(dateTimeDateFormatter)
        val time = dateTime.format(dateTimeTimeFormatter)
        val hour = time.split(":").first().toInt()
        val suffix = if (hour >= 12) "오후" else "오전"
        return "$date $suffix $time"
    }
}
