package com.core.domain.repository

import com.core.model.Account
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(id: String, pwd: String): Flow<Account>
    fun bioLogin(id: String): Flow<Account?>
}
