package com.core.domain.repository

import com.core.model.Account
import com.core.model.Device
import kotlinx.coroutines.flow.Flow

interface WearableRepository {
    fun getWearableConnectState(): Flow<Device?>
    suspend fun sendLogin(account: Account)
    suspend fun requestCollectHeartRate(id: String)
}
