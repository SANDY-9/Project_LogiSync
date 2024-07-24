package com.core.data.repository.prefs

import com.core.data.mapper.toDevice
import com.core.domain.repository.DevicePrefsRepository
import com.core.model.Device
import com.sandy.datastore.DeviceDataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

// 온보딩 단계에서 기기 정보 저장
class DevicePrefsRepositoryImpl @Inject constructor(
    private val deviceDataStoreManager: DeviceDataStoreManager,
) : DevicePrefsRepository {
    override fun getLastPairedDevice(): Flow<Device?> {
        return deviceDataStoreManager.getLastConnectedDevice().map {
            it?.toDevice()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updatePairedDevice(
        name: String,
        alias: String?,
        id: String,
    ) {
        withContext(Dispatchers.IO) {
            deviceDataStoreManager.updateLastConnectedDevice(name, alias, id)
        }
    }

    override fun getIsInitialConnectState(): Flow<Boolean> {
        return deviceDataStoreManager.getIsInitialConnect().flowOn(Dispatchers.IO)
    }

    override suspend fun getIsInitialConnect(): Boolean {
        return withContext(Dispatchers.IO) {
            deviceDataStoreManager.getIsInitialConnect().first()
        }
    }

    override suspend fun updateInitialConnect() {
        withContext(Dispatchers.IO) {
            deviceDataStoreManager.updateInitialConnect()
        }
    }
    override suspend fun initiateConnect() {
        withContext(Dispatchers.IO) {
            deviceDataStoreManager.initiateConnect()
        }
    }
}
