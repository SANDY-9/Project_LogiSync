package com.core.data.repository.network

import com.core.data.mapper.toArrestList
import com.core.domain.repository.GetArrestRepository
import com.core.firebase.ArrestClient
import com.core.model.Arrest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetArrestRepositoryImpl @Inject constructor(
    private val arrestClient: ArrestClient,
) : GetArrestRepository {
    override fun getLastMyArrestList(id: String): Flow<List<Arrest>> = callbackFlow {
        arrestClient.getLastMyArrestList(
            id = id,
            onSuccess = {
                trySend(it?.toArrestList() ?: emptyList())
            },
            onError = { e ->
                close(e)
            },
        )
        awaitClose()
    }

    override fun getMyArrestList(id: String): Flow<List<Arrest>> = callbackFlow  {
        arrestClient.getMyArrestList(
            id = id,
            onSuccess = {
                trySend(it?.toArrestList() ?: emptyList())
            },
            onError = { e ->
                close(e)
            }
        )
        awaitClose()
    }
    override fun getArrestList(): Flow<List<Arrest>> = callbackFlow  {
        arrestClient.getArrestList(
            onSuccess = {
                trySend(it?.toArrestList() ?: emptyList())
            },
            onError = { e ->
                close(e)
            }
        )
        awaitClose()
    }
}
