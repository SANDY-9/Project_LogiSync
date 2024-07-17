package com.sandy.logisync.wearable.health

import androidx.health.services.client.data.Availability
import java.time.LocalDateTime

data class HeartRateDTO(
    val date: LocalDateTime,
    val value: Double,
)

sealed class MeasureMessage {
    class MeasureAvailability(val availability: Availability) : MeasureMessage()
    class MeasuredHeartRateData(val data: HeartRateDTO) : MeasureMessage()
}
