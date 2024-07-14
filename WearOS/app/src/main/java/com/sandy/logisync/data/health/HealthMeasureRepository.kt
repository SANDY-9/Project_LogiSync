package com.sandy.logisync.data.health

import com.sandy.logisync.model.MeasuredHeartRate
import kotlinx.coroutines.flow.Flow

interface HealthMeasureRepository {
    fun getMeasuredHeartRate(): Flow<MeasuredHeartRate>
}
