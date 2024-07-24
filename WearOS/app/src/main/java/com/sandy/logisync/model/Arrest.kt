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
        HEART_RATE_HIGH, HEART_RATE_LOW, NORMAL;

        companion object {
            fun getDesc(type: ArrestType): String {
                return when (type) {
                    HEART_RATE_HIGH -> "심박수 임계치 초과"
                    HEART_RATE_LOW -> "심박수 임계치 미만"
                    NORMAL -> "위급 상황 신고"
                }
            }
        }
    }
}
