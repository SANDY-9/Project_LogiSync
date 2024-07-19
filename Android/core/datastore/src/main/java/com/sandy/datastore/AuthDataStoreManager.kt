package com.sandy.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sandy.datastore.extensions.editPrefs
import com.sandy.datastore.extensions.getPrefs
import com.sandy.datastore.extensions.toAccountDTO
import com.sandy.datastore.extensions.toJson
import com.sandy.datastore.model.AccountDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun updateLoginAccount(
        id: String,
        name: String,
        tel: String,
        duty: String,
        team: String,
    ) {
        val account = AccountDTO(id, name, tel, duty, team)
        dataStore.editPrefs(
            key = PrefsKeys.ACCOUNT,
            value = account.toJson()
        )
    }

    fun getAccount(): Flow<AccountDTO> {
        return dataStore.getPrefs(
            key = PrefsKeys.ACCOUNT,
            defaultValue = ""
        ).map { it.toAccountDTO() }
    }

    private object PrefsKeys {
        val ACCOUNT = stringPreferencesKey("account")
    }
}
