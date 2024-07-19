package com.sandy.logisync.domain

import com.sandy.logisync.data.network.NetworkRepository
import com.sandy.logisync.model.Arrest
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetArrestCriticalPointUseCase @Inject constructor (
    private val networkRepository: NetworkRepository,
) {
    suspend operator fun invoke(id: String, bpm: Int): Arrest.ArrestType {
        val criticalPoint = networkRepository.getHeartRateCriticalPoint(id).first()
        return when {
            bpm < criticalPoint.min -> Arrest.ArrestType.HEART_RATE_LOW
            bpm > criticalPoint.max -> Arrest.ArrestType.HEART_RATE_HIGH
            else -> Arrest.ArrestType.NONE
        }
    }

}
