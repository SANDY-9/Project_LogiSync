package com.core.domain.usecases.wearable

import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.repository.WearableRepository
import com.core.model.Device
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetWearableConnectStateUseCase @Inject constructor(
    private val wearableRepository: WearableRepository,
    private val devicePrefsRepository: DevicePrefsRepository,
) {
    operator fun invoke(): Flow<Device?> {
        return wearableRepository.getWearableConnectState().onEach {
            it?.let { device ->
                devicePrefsRepository.updatePairedDevice(
                    name = device.name,
                    alias = device.alias,
                    id = device.id
                )
            }
        }
    }
}
