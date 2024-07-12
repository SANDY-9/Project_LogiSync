package com.core.domain.usecases.prefs

import com.core.domain.repository.DevicePrefsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPairedDeviceNameUseCase @Inject constructor(
    private val devicePrefsRepository: DevicePrefsRepository
) {
    operator fun invoke(): Flow<String> {
        return devicePrefsRepository.getPairedDeviceName()
    }
}
