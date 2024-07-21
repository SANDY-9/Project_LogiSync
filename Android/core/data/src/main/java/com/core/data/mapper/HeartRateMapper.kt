package com.core.data.mapper

import com.core.firebase.model.HeartRateDTO
import com.core.model.HeartRate

internal fun Map<String, Int>.toHeartRateList(): List<HeartRate> {
    return map { (key, value) ->
        HeartRate(
            bpm = value,
            date = key
        )
    }.sortedByDescending { it.date }
}

internal fun HeartRateDTO.toHeartRate(): HeartRate {
    return HeartRate(
        bpm = bpm,
        date = date,
    )
}
