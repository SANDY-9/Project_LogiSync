package com.sandy.logisync.model

data class MeasuredHeartRate(
    val availability: MeasuredAvailability,
    val heartRate: HeartRate?
)

enum class MeasuredAvailability {
    LOADING,
    AVAILABLE,
    UNAVAILABLE,
    UNAVAILABLE_DEVICE_OFF_BODY,
}
