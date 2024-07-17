package com.sandy.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sandy.datastore.extensions.editPrefs
import com.sandy.datastore.extensions.getPrefs
import com.sandy.datastore.extensions.toDeviceDTO
import com.sandy.datastore.extensions.toJson
import com.sandy.datastore.model.DeviceDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeviceDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun updateLastConnectedDevice(
        name: String,
        alias: String?,
        id: String,
    ) {
        val deviceDTO = DeviceDTO(name, alias ?: "", id)
        dataStore.editPrefs(
            key = PrefsKeys.LAST_CONNECTED_DEVICE,
            value = deviceDTO.toJson()
        )
    }

    fun getLastConnectedDevice(): Flow<DeviceDTO> {
        return dataStore.getPrefs(
            key = PrefsKeys.LAST_CONNECTED_DEVICE,
            defaultValue = ""
        ).map {
            it.toDeviceDTO()
        }
    }

    private object PrefsKeys {
        val LAST_CONNECTED_DEVICE = stringPreferencesKey("last_connected_device")
    }
}
