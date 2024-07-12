package com.core.domain.usecases.wearable

import com.core.domain.repository.WearableRepository
import javax.inject.Inject

class CollectHeartRateUseCase @Inject constructor(
    private val wearableRepository: WearableRepository
) {
    suspend operator fun invoke(id: String): Boolean {
        try {
            wearableRepository.requestCollectHeartRate(id)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
