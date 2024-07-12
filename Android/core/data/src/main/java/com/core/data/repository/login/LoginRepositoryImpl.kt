package com.core.data.repository.login

import com.core.domain.repository.LoginRepository
import com.core.firebase.AuthClient
import com.core.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val authClient: AuthClient,
) : LoginRepository {
    override fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String
    ): Flow<Boolean> = callbackFlow {
        authClient.signup(id, pwd, name, tel, duty) { isSuccess ->
            trySend(isSuccess)
        }
        awaitClose()
    }

    override fun checkTel(tel: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun checkId(id: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun login(id: String, pwd: String): Flow<Account> = callbackFlow {
        authClient.login(
            id = id,
            pwd = pwd,
            onLogin = { account ->
                trySend(account)
            },
            onError = { error ->
                close(error)
            }
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)
}
