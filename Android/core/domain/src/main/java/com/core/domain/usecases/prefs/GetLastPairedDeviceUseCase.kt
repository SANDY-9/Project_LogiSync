package com.core.domain.usecases.prefs

import com.core.domain.repository.DevicePrefsRepository
import com.core.model.Device
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastPairedDeviceUseCase @Inject constructor(
    private val devicePrefsRepository: DevicePrefsRepository
) {
    operator fun invoke(): Flow<Device?> {
        return devicePrefsRepository.getLastPairedDevice()
    }
}
