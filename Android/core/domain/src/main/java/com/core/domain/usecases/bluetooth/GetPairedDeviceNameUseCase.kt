package com.core.domain.usecases.bluetooth

import com.core.domain.repository.BluetoothRepository
import javax.inject.Inject

class GetPairedDeviceNameUseCase @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) {
    suspend operator fun invoke(): String {
        return bluetoothRepository.getPairedDeviceName()
    }
}
