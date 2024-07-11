package com.core.data.repository.bluetooth

import com.core.domain.repository.BluetoothRepository
import com.core.enum.BluetoothState
import com.sandy.bluetooth.MyBluetoothManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val INTERVAL = 1200L

class BluetoothRepositoryImpl @Inject constructor(
    private val bluetoothManager: MyBluetoothManager,
) : BluetoothRepository {

    override fun getBluetoothState(): Flow<BluetoothState> = flow {
        while (true) {
            val bluetoothState = bluetoothManager.getBluetoothState()
            emit(bluetoothState)
            delay(INTERVAL)
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    override suspend fun isPairedDevice(): Boolean {
        return withContext(Dispatchers.IO) {
            val device = bluetoothManager.getBluetoothPairedWatch()
            return@withContext device.isNotEmpty()
        }
    }

    override suspend fun getPairedDeviceName(): String {
        return withContext(Dispatchers.IO) {
            val device = bluetoothManager.getBluetoothPairedWatch()
            val deviceName =
                if (device.isNotEmpty()) bluetoothManager.getDeviceName(device[0]) else ""
            return@withContext deviceName
        }
    }
}
