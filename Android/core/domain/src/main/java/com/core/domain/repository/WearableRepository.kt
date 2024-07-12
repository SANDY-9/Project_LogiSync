package com.core.domain.repository

import com.core.model.Device
import kotlinx.coroutines.flow.Flow

interface WearableRepository {
    fun getWearableConnectState(): Flow<Device?>
    suspend fun sendLogin(id: String)
    suspend fun requestCollectHeartRate(id: String)
}
