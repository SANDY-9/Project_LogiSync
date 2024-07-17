package com.sandy.logisync.data.datastore

import com.sandy.logisync.model.Account
import com.sandy.logisync.model.HeartRate
import kotlinx.coroutines.flow.Flow

interface WearableDataStoreRepository {
    suspend fun registerAccount(account: String)
    fun getAccount(): Flow<Account?>

    suspend fun updateLastHeartRate(heartRate: HeartRate)
    fun getLastHeartRate(): Flow<HeartRate?>
}
