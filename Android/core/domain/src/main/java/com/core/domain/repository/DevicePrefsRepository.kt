package com.core.domain.repository

import com.core.model.Device
import kotlinx.coroutines.flow.Flow

interface DevicePrefsRepository {
    fun getLastPairedDevice(): Flow<Device?>
    suspend fun updatePairedDevice(
        name: String,
        alias: String?,
        id: String,
    )
}
