package com.core.data.mapper

import com.core.model.HeartRate

internal fun Map<String, Int>.toHeartRateList(): List<HeartRate> {
    return map { (key, value) ->
        HeartRate(
            bpm = value,
            date = key
        )
    }
}
