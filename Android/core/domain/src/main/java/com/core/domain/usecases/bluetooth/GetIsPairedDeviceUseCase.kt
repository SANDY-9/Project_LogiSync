package com.core.domain.usecases.bluetooth

import com.core.domain.repository.BluetoothRepository
import javax.inject.Inject

class GetIsPairedDeviceUseCase @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) {
    suspend operator fun invoke(): Boolean {
        return bluetoothRepository.isPairedDevice()
    }
}
