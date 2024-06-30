package com.core.data.repository.user

import com.core.model.Account
import kotlinx.coroutines.flow.Flow

interface SignupRepository {
    fun checkTel(tel: String): Flow<Boolean>
    fun checkId(id: String): Flow<Boolean>
    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: Account.Duty,
    ): Flow<Account>
}
