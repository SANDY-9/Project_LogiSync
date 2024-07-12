package com.sandy.logisync.data.datastore

import com.sandy.logisync.model.Account
import kotlinx.coroutines.flow.Flow

interface WearableDataStoreRepository {
    suspend fun registerAccount(account: String)
    fun getAccount(): Flow<Account?>
}
