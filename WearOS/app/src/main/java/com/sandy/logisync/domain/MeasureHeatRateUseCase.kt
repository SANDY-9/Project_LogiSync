package com.sandy.logisync.domain

import android.location.Location
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.data.health.HealthMeasureRepository
import com.sandy.logisync.data.location.LocationRepository
import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.data.wearable.WearableTranscriptionRepository
import com.sandy.logisync.model.Arrest
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.model.MeasuredHeartRate
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeasureHeatRateUseCase @Inject constructor(
    private val healthMeasureRepository: HealthMeasureRepository,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
    private val networkRepository: NetworkRepository,
    private val wearableTranscriptionRepository: WearableTranscriptionRepository,
    private val locationRepository: LocationRepository,
) {

    suspend operator fun invoke(id: String) : Flow<MeasuredHeartRate> {
        return healthMeasureRepository.getMeasuredHeartRate().onEach {
            it.heartRate?.let { heartRate ->
                // 로컬에 최근 심박수 저장
                updateLastHeartRateToLocal(heartRate)
                // 서버에 심박수 저장
                updateHeartRateToServer(id, heartRate)
                // 기기 전송
                sendHeartRateToDevice(heartRate)
                // 심박수 임계점 확인
                checkCriticalPoint(id, heartRate)
            }
        }
    }

    // 로컬에 최근 심박수 저장
    private suspend fun updateLastHeartRateToLocal(heartRate: HeartRate) {
        coroutineScope {
            wearableDataStoreRepository.updateLastHeartRate(heartRate)
        }
    }

    // 서버에 심박수 저장
    private suspend fun updateHeartRateToServer(id: String, heartRate: HeartRate) {
        coroutineScope {
            networkRepository.updateHeartRate(id, heartRate.bpm, heartRate.time).first()
        }
    }

    // 기기 전송
    private suspend fun sendHeartRateToDevice(heartRate: HeartRate) {
        coroutineScope {
            wearableTranscriptionRepository.resultMeasuredHeartRate(heartRate)
        }
    }

    private suspend fun checkCriticalPoint(id: String, heartRate: HeartRate) {
        coroutineScope {
            val criticalPoint = networkRepository.getHeartRateCriticalPoint(id).first()
            if(criticalPoint.min > heartRate.bpm) {
                arrestHeartRate(id, Arrest.ArrestType.HEART_RATE_LOW, heartRate.bpm)
            }
            if(criticalPoint.max < heartRate.bpm) {
                arrestHeartRate(id, Arrest.ArrestType.HEART_RATE_HIGH, heartRate.bpm)
            }
        }
    }

    private suspend fun arrestHeartRate(
        id: String,
        arrestType: Arrest.ArrestType,
        bpm: Int
    ) {
        val location = locationRepository.getLastLocation().first()
        // 신고 서버 저장
        updateArrestToServer(id, arrestType, location, bpm)
        // 신고 푸시 알림
        networkRepository.notifyArrest(id).first()
    }

    private suspend fun updateArrestToServer(
        id: String,
        arrestType: Arrest.ArrestType,
        location: Location,
        bpm: Int
    ) {
        coroutineScope {
            networkRepository.updateHeartBeatArrest(id, arrestType, location, bpm).first()
        }
    }

}
