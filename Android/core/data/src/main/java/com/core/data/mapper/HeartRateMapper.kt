package com.core.data.mapper

import com.core.firebase.model.HeartRateDTO
import com.core.model.HeartRate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal fun Map<String, Int>.toHeartRateList(): List<HeartRate> {
    return map { (key, value) ->
        HeartRate(
            bpm = value,
            date = LocalDateTime.parse(key, formatter)
        )
    }.sortedByDescending { it.date }
}

internal fun List<HeartRateDTO>.toHeartRateList(): List<HeartRate> {
    return map {
        HeartRate(
            bpm = it.bpm,
            date = LocalDateTime.parse(it.date, formatter)
        )
    }.sortedByDescending { it.date }
}

internal fun HeartRateDTO.toHeartRate(): HeartRate {
    return HeartRate(
        bpm = bpm,
        date = LocalDateTime.parse(date, formatter),
    )
}

private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm")
