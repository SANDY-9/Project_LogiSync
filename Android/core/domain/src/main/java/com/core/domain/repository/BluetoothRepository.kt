package com.core.domain.repository

import com.core.enum.BluetoothState
import kotlinx.coroutines.flow.Flow

interface BluetoothRepository {

    fun getBluetoothState(): Flow<BluetoothState>
    suspend fun isPairedDevice(): Boolean
    suspend fun getPairedDeviceName(): String
}
