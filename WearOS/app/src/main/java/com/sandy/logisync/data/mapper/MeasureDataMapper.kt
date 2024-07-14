package com.sandy.logisync.data.mapper

import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredAvailability
import com.sandy.logisync.model.MeasuredHeartRate
import com.sandy.logisync.wearable.health.HeartRateDTO
import com.sandy.logisync.wearable.health.MeasureMessage

internal fun MeasureMessage.toMeasuredHeartRate(): MeasuredHeartRate {
    return when (this) {
        is MeasureMessage.MeasureAvailability -> MeasuredHeartRate(
            availability = MeasuredAvailability.valueOf(availability.toString()),
            heartRate = null
        )

        is MeasureMessage.MeasuredHeartRateData -> MeasuredHeartRate(
            availability = MeasuredAvailability.AVAILABLE,
            heartRate = data.toHeartRate()
        )
    }
}

private fun HeartRateDTO.toHeartRate(): HeartRate {
    return HeartRate(
        date = date,
        value = value.toInt(),
    )
}
