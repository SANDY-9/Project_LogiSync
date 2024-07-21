package com.core.model

import com.core.utils.DateUtil

data class HeartRate(
    val bpm: Int = 0,
    val date: String = "",
    val avgRange: AvgRange = when(bpm) {
        in 0 until 60 -> AvgRange.LOW
        in 60..100 -> AvgRange.NORMAL
        else -> AvgRange.HIGH
    }
) {
    fun time(): String {
        return DateUtil.convertTime(date)
    }
    fun hour(): Int {
        return DateUtil.getHour(date)
    }
    enum class AvgRange {
        HIGH, LOW, NORMAL,
    }
}
