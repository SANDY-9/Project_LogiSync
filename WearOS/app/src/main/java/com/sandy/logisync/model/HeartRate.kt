package com.sandy.logisync.model

import java.time.LocalDateTime

data class HeartRate(
    val date: LocalDateTime,
    val value: Int,
) {
    fun timeStr() {
    }
}
