package com.core.domain.usecases.wearable

import com.core.domain.repository.DevicePrefsRepository
import com.core.domain.repository.WearableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWearableConnectStateUseCase @Inject constructor(
    private val wearableRepository: WearableRepository,
    private val devicePrefsRepository: DevicePrefsRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return wearableRepository.getWearableConnectState().map {
            it?.let { device ->
                devicePrefsRepository.updatePairedDevice(
                    name = device.name,
                    alias = device.alias,
                    id = device.id
                )
            }
            it?.isNearby ?: false
        }
    }
}
