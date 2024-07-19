package com.core.data.repository.auth

import com.core.domain.repository.SignupRepository
import com.core.firebase.AuthClient
import com.core.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val authClient: AuthClient,
) : SignupRepository {

    override fun signup(id: String, pwd: String, name: String, tel: String): Flow<Account> = callbackFlow {
        authClient.signup(
            id = id,
            pwd = pwd,
            name = name,
            tel = tel,
            onSuccess = { account ->
                trySend(account)
            },
            onError = {
                close(it)
            }
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun checkTel(tel: String): Flow<Boolean> = callbackFlow {
        authClient.checkTel(
            tel = tel,
            onSuccess = { exist ->
                trySend(exist)
            },
            onError = {
                error(it)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun checkId(id: String): Flow<Boolean> = callbackFlow {
        authClient.checkId(
            id = id,
            onSuccess = { exist ->
                trySend(exist)
            },
            onError = {
                error(it)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)
    
}
