package com.sandy.logisync.data.health

import com.sandy.logisync.data.mapper.toMeasuredHeartRate
import com.sandy.logisync.model.MeasuredHeartRate
import com.sandy.logisync.wearable.health.HeartRateServiceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HealthMeasureRepositoryImpl @Inject constructor(
    private val heartRateServiceManager: HeartRateServiceManager,
) : HealthMeasureRepository {
    override fun getMeasuredHeartRate(): Flow<MeasuredHeartRate> {
        return heartRateServiceManager.getHeartRateMeasureFlow().map {
            it.toMeasuredHeartRate()
        }
    }
}
