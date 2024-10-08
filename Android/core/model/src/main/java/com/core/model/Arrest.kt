package com.core.model

import com.core.utils.DateUtil
import java.io.Serializable
import java.time.LocalDateTime

data class Arrest(
    val id: String,
    val name: String,
    val tel: String,
    val time: LocalDateTime,
    val lat: Double,
    val lng: Double,
    val bpm: Int?,
    val arrestType: ArrestType,
): Serializable {
    fun date(): String {
        return DateUtil.convertDateTime(time)
    }
    enum class ArrestType(val desc: String = "") {
        HEART_RATE_HIGH("심박수 임계치 초과"),
        HEART_RATE_LOW("심박수 임계치 미만"),
        NORMAL("위급상황 신고"),
    }
}
