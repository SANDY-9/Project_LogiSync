package com.core.domain.repository

import com.core.model.Account
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
        duty: String,
    ): Flow<Boolean>

    fun checkTel(tel: String): Flow<Boolean>
    fun checkId(id: String): Flow<Boolean>
    fun login(id: String, pwd: String): Flow<Account>
}
