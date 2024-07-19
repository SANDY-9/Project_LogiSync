package com.sandy.logisync.domain

import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredHeartRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeasureHeatRateUseCase @Inject constructor(
    private val healthMeasureRepository: HealthMeasureRepository,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val networkRepository: NetworkRepository,
) {
    suspend operator fun invoke(id: String, token: String) : Flow<MeasuredHeartRate> {
        return healthMeasureRepository.getMeasuredHeartRate().onEach {
            it.heartRate?.let { heartRate ->
                // 로컬에 최근 심박수 저장
                updateLastHeartRateToLocal(heartRate)
                // 서버에 심박수 저장
                updateHeartRateToServer(id, heartRate)
            }
        }
    }

    // 로컬에 최근 심박수 저장
    private suspend fun updateLastHeartRateToLocal(heartRate: HeartRate) {
        wearableDataStoreRepository.updateLastHeartRate(heartRate)
    }

    // 서버에 심박수 저장
    private suspend fun updateHeartRateToServer(id: String, heartRate: HeartRate) {
        networkRepository.updateHeartRate(id, heartRate.bpm, heartRate.time).first()
    }

}
