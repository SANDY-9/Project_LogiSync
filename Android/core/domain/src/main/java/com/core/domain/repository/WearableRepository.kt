package com.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface WearableRepository {
    fun getWearableConnectState(): Flow<Boolean>
}
