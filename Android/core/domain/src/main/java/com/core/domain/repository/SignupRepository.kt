package com.core.domain.repository

import com.core.model.Account
import kotlinx.coroutines.flow.Flow

interface SignupRepository {
    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
    ): Flow<Account>
    fun checkTel(tel: String): Flow<Boolean>
    fun checkId(id: String): Flow<Boolean>
}
