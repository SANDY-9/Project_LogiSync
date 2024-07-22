package com.core.data.repository.network

import com.core.data.mapper.toUser
import com.core.data.mapper.toUserMap
import com.core.domain.repository.GetUserRepository
import com.core.firebase.UserClient
import com.core.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(
    private val userClient: UserClient,
) : GetUserRepository {

    override fun getUser(id: String): Flow<User?> = callbackFlow {
        userClient.getUser(
            id = id,
            onSuccess = {
                trySend(it?.toUser(id))
            },
            onError = {
                close(it)
            },
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun getUserList(): Flow<Map<User.Team, List<User>>?> = callbackFlow {
        userClient.getUserList(
            onSuccess = {
                trySend(it.toUserMap())
            },
            onError = {
                close(it)
            },
        )
        awaitClose()
    }

}
