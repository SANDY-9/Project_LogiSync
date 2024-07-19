package com.sandy.logisync.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sandy.logisync.data.mapper.toAccount
import com.sandy.logisync.data.mapper.toHeartRate
import com.sandy.logisync.data.mapper.toJson
import com.sandy.logisync.model.Account
import com.sandy.logisync.model.HeartRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WearableDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : WearableDataStoreRepository {

    override suspend fun registerAccount(account: String) {
        dataStore.editPrefs(
            key = PrefsKeys.ACCOUNT,
            value = account,
        )
    }

    override suspend fun getAccount(): Account? {
        return dataStore.getPrefs(
            key = PrefsKeys.ACCOUNT,
            defaultValue = ""
        ).map {
            if (it.isNotBlank()) it.toAccount() else null
        }.first()
    }

    override suspend fun updateLastHeartRate(heartRate: HeartRate) {
        dataStore.editPrefs(
            key = PrefsKeys.LAST_HEART_RATE,
            value = heartRate.toJson()
        )
    }

    override fun getLastHeartRate(): Flow<HeartRate?> {
        return dataStore.getPrefs(
            key = PrefsKeys.LAST_HEART_RATE,
            defaultValue = ""
        ).map {
            if (it.isNotBlank()) it.toHeartRate() else null
        }
    }

    private object PrefsKeys {
        val ACCOUNT = stringPreferencesKey("account")
        val LAST_HEART_RATE = stringPreferencesKey("last_heart_rate")
    }
}
