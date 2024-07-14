package com.sandy.logisync.data.health

import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.mapper.toMeasuredHeartRate
import com.sandy.logisync.model.MeasuredHeartRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HealthMeasureRepositoryImpl @Inject constructor(
    private val heartRateServiceManager: HeartRateServiceManager,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
) : HealthMeasureRepository {

    override fun getMeasuredHeartRate(): Flow<MeasuredHeartRate> {
        return heartRateServiceManager.getHeartRateMeasureFlow().map {
            it.toMeasuredHeartRate().also { measuredHeartRate ->
                measuredHeartRate.heartRate?.let { heartRate ->
                    wearableDataStoreRepository.updateLastHeartRate(heartRate)
                }
            }
        }
    }
}
