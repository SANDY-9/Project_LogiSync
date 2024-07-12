package com.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface WearableRepository {
    fun getWearableConnectState(): Flow<Boolean>
    suspend fun sendLogin(id: String)
    suspend fun requestCollectHeartRate(id: String)
}
