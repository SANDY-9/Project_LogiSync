package com.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DevicePrefsRepository {
    fun getPairedDeviceName(): Flow<String>
}
