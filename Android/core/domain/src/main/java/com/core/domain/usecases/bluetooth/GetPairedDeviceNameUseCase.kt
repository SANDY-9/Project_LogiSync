package com.core.domain.usecases.bluetooth

import com.core.domain.repository.BluetoothRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPairedDeviceNameUseCase @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) {
    operator fun invoke(): Flow<String> {
        return bluetoothRepository.getPairedDeviceName()
    }
}
