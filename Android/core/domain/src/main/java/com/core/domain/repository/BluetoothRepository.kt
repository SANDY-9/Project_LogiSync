package com.core.domain.repository

import com.core.domain.enums.BluetoothState
import kotlinx.coroutines.flow.Flow

interface BluetoothRepository {

    fun getBluetoothState(): Flow<BluetoothState>
    suspend fun isPairedDevice(): Boolean
    fun getPairedDeviceName(): Flow<String>
}
