package com.core.domain.usecases.wearable

import com.core.domain.repository.WearableRepository
import javax.inject.Inject

class LoginWearableUseCase @Inject constructor(
    private val wearableRepository: WearableRepository
) {
    suspend fun invoke(id: String): Boolean {
        try {
            wearableRepository.sendLogin(id)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
