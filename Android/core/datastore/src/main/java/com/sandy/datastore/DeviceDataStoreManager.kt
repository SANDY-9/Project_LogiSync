package com.sandy.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sandy.datastore.extensions.editPrefs
import com.sandy.datastore.extensions.getPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun updateLastConnectedDevice(deviceName: String) {
        dataStore.editPrefs(
            key = PrefsKeys.LAST_CONNECTED_DEVICE,
            value = deviceName
        )
    }

    fun getLastConnectedDevice(): Flow<String> {
        return dataStore.getPrefs(
            key = PrefsKeys.LAST_CONNECTED_DEVICE,
            defaultValue = ""
        )
    }

    private object PrefsKeys {
        val LAST_CONNECTED_DEVICE = stringPreferencesKey("last_connected_device")
    }
}
