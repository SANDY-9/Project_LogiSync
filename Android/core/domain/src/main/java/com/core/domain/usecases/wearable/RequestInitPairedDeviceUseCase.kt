package com.core.domain.usecases.wearable

import com.core.domain.repository.DevicePrefsRepository
import javax.inject.Inject

class RequestInitPairedDeviceUseCase @Inject constructor(
    private val devicePrefsRepository: DevicePrefsRepository
) {
    suspend operator fun invoke() {
        devicePrefsRepository.initiateConnect()
    }
}
