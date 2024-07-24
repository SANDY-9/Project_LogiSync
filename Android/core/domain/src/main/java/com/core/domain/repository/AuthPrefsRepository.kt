package com.core.domain.repository

import com.core.model.Account
import kotlinx.coroutines.flow.Flow

interface AuthPrefsRepository {

    suspend fun updateAccount(account: Account)
    fun getAccount(): Flow<Account?>
    suspend fun logoutAccount()

    suspend fun getFingerPrint(): String?
    suspend fun registerFingerPrint(id: String)
}
