package com.core.data.repository.prefs

import com.core.data.mapper.toAccount
import com.core.domain.repository.AuthPrefsRepository
import com.core.model.Account
import com.sandy.datastore.AuthDataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthPrefsRepositoryImpl @Inject constructor(
    private val authDataStoreManager: AuthDataStoreManager,
) : AuthPrefsRepository {

    override suspend fun updateAccount(account: Account) {
        authDataStoreManager.updateLoginAccount(
            id = account.id,
            name = account.name,
            tel = account.tel,
            duty = account.duty.name
        )
    }

    override fun getAccount(): Flow<Account> {
        return authDataStoreManager.getAccount().map { dto ->
            dto.toAccount()
        }
    }

    override suspend fun logoutAccount() {
        TODO("Not yet implemented")
    }
}
