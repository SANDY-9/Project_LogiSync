package com.core.data.repository.prefs

import com.core.domain.repository.DevicePrefsRepository
import com.sandy.datastore.DeviceDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// 온보딩 단계에서 기기 정보 저장
class DevicePrefsRepositoryImpl @Inject constructor(
    private val deviceDataStoreManager: DeviceDataStoreManager,
) : DevicePrefsRepository {
    override fun getPairedDeviceName(): Flow<String> {
        return deviceDataStoreManager.getLastConnectedDevice()
    }
}
