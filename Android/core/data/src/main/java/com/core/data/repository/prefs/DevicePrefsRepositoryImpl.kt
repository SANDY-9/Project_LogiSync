package com.core.data.repository.prefs

import com.core.data.mapper.toDevice
import com.core.domain.repository.DevicePrefsRepository
import com.core.model.Device
import com.sandy.datastore.DeviceDataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

// 온보딩 단계에서 기기 정보 저장
class DevicePrefsRepositoryImpl @Inject constructor(
    private val deviceDataStoreManager: DeviceDataStoreManager,
) : DevicePrefsRepository {
    override fun getPairedDeviceName(): Flow<Device> {
        return deviceDataStoreManager.getLastConnectedDevice().map {
            it.toDevice()
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
}
