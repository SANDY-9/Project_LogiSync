package com.core.model

import com.core.utils.DateUtil
import java.time.LocalDateTime

data class HeartRate(
    val bpm: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    val avgRange: AvgRange = when(bpm) {
        in 0 until 60 -> AvgRange.LOW
        in 60..100 -> AvgRange.NORMAL
        else -> AvgRange.HIGH
    }
) {
    fun time(): String {
        return DateUtil.convertTime(date)
    }
    enum class AvgRange {
        HIGH, LOW, NORMAL,
    }
}
