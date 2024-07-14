package com.sandy.logisync.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sandy.logisync.data.mapper.toAccount
import com.sandy.logisync.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WearableDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : WearableDataStoreRepository {

    override suspend fun registerAccount(account: String) {
        Log.e("확인", "registerAccount: $account")
        dataStore.editPrefs(
            key = PrefsKeys.ACCOUNT,
            value = account,
        )
    }

    override fun getAccount(): Flow<Account?> {
        return dataStore.getPrefs(
            key = PrefsKeys.ACCOUNT,
            defaultValue = ""
        ).map {
            if (it.isNotBlank()) it.toAccount() else null
        }
    }

    private object PrefsKeys {
        val ACCOUNT = stringPreferencesKey("account")
    }
}
