package com.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SignupRepository {
    fun signup(
        id: String,
        pwd: String,
        name: String,
        tel: String,
    ): Flow<Boolean>
    fun checkTel(tel: String): Flow<Boolean>
    fun checkId(id: String): Flow<Boolean>
}
