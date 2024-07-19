package com.sandy.logisync.model

import com.sandy.logisync.model.utils.DateUtil
import java.time.LocalDateTime

data class HeartRate(
    val bpm: Int,
    val time: LocalDateTime,
) {
    fun dateStr(): String {
        return DateUtil.convertDate(time)
    }
    fun timeStr(): String {
        return DateUtil.convertTime(time)
    }
}
