package com.core.domain.usecases.bluetooth

import com.core.domain.repository.BluetoothRepository
import com.core.enum.BluetoothState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBluetoothStateUseCase @Inject constructor(
    private val bluetoothRepository: BluetoothRepository
) {
    operator fun invoke(): Flow<BluetoothState> {
        return bluetoothRepository.getBluetoothState()
    }
}
