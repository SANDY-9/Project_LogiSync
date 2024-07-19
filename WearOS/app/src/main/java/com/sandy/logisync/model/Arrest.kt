package com.sandy.logisync.model

import java.time.LocalDateTime

data class Arrest(
    val id: String,
    val time: LocalDateTime,
    val lat: Double,
    val lng: Double,
    val arrestType: ArrestType,
) {
    enum class ArrestType {
        HEART_RATE_UPPER, HEART_RATE_LOWER, NORMAL,
    }
}
