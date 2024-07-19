package com.sandy.logisync.data.health

import com.sandy.logisync.data.mapper.toMeasuredHeartRate
import com.sandy.logisync.model.MeasuredHeartRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HealthMeasureRepositoryImpl @Inject constructor(
    private val heartRateServiceManager: HeartRateServiceManager,
) : HealthMeasureRepository {

    override suspend fun getMeasuredHeartRate(): Flow<MeasuredHeartRate> {
        return withContext(Dispatchers.Main) {
            heartRateServiceManager.getHeartRateMeasureFlow().map {
                it.toMeasuredHeartRate()
            }.flowOn(Dispatchers.Main)
        }
    }
}
