package com.core.data.repository.prefs

import com.core.data.mapper.toAccount
import com.core.domain.repository.AuthPrefsRepository
import com.core.model.Account
import com.sandy.datastore.AuthDataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthPrefsRepositoryImpl @Inject constructor(
    private val authDataStoreManager: AuthDataStoreManager,
) : AuthPrefsRepository {

    override suspend fun updateAccount(account: Account) {
        withContext(Dispatchers.IO) {
            authDataStoreManager.updateLoginAccount(
                id = account.id,
                name = account.name,
                tel = account.tel,
                duty = account.duty.name,
                team = account.team.name
            )
        }
    }

    override fun getAccount(): Flow<Account> {
        return authDataStoreManager.getAccount().map {
            it.toAccount()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun logoutAccount() {
        TODO("Not yet implemented")
    }
}
