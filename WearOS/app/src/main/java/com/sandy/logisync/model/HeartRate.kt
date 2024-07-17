package com.sandy.logisync.model

import java.time.LocalDateTime

data class HeartRate(
    val bpm: Int,
    val time: LocalDateTime,
) {
    fun timeStr() {
    }
}
