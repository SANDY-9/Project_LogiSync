package com.core.data.repository.user.impl

import com.core.data.repository.user.SignupRepository
import com.core.firebase.UserDataSource
import com.core.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class SignupRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : SignupRepository {

    override fun checkTel(tel: String): Flow<Boolean> {
        return flow {
            userDataSource.checkTel(tel) { existed ->
                runBlocking { emit(existed) }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun checkId(id: String): Flow<Boolean> {
        return flow {
            userDataSource.checkId(id) { existed ->
                runBlocking { emit(existed) }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: Account.Duty,
    ): Flow<Account> {
        return flow {
            userDataSource.signup(
                id = id,
                pwd = pwd,
                name = name,
                tel = tel,
                duty = duty.name,
            ) { isSuccess ->
                if (isSuccess) {
                    runBlocking {
                        emit(
                            Account(
                                id = id,
                                pwd = pwd,
                                name = name,
                                tel = tel,
                                duty = duty,
                            )
                        )
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
