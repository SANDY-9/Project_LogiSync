package com.core.domain.repository

import com.core.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getUser(id: String): Flow<User?>
    fun getUserList(): Flow<Map<User.Team, List<User>>?>
}
