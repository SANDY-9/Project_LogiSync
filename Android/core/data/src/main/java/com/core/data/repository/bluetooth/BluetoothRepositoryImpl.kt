package com.core.data.repository.bluetooth

import com.core.domain.enums.BluetoothState
import com.core.domain.repository.BluetoothRepository
import com.sandy.bluetooth.MyBluetoothManager
import com.sandy.bluetooth.utils.BluetoothDisabledException
import com.sandy.bluetooth.utils.BluetoothPermissionDeniedException
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
            try {
                val bluetoothEnabled = bluetoothManager.isBluetoothEnabled()
                emit(
                    if (bluetoothEnabled) BluetoothState.ON else BluetoothState.OFF
                )
            } catch (e: Exception) {
                when (e) {
                    is BluetoothDisabledException -> emit(BluetoothState.DISABLED)
                    is BluetoothPermissionDeniedException -> emit(BluetoothState.PERMISSION_DENIED)
                    else -> emit(BluetoothState.ERROR)
                }
            } finally {
                delay(INTERVAL)
            }
        }
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    override suspend fun isPairedDevice(): Boolean {
        return withContext(Dispatchers.IO) {
            val device = bluetoothManager.getBluetoothPairedWatch()
            return@withContext true //device.isNotEmpty()
        }
    }

    override fun getPairedDeviceName(): Flow<String> = flow {
        val device = bluetoothManager.getBluetoothPairedWatch()
        val deviceName = if (device.isNotEmpty()) bluetoothManager.getDeviceName(device[0]) else ""
        emit(deviceName)
    }.flowOn(Dispatchers.IO)
}
