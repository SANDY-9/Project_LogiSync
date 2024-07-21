package com.core.data.repository.network

import com.core.data.mapper.toArrestList
import com.core.data.mapper.toArrestMap
import com.core.domain.repository.GetArrestRepository
import com.core.firebase.ArrestClient
import com.core.model.Arrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDate
import javax.inject.Inject

class GetArrestRepositoryImpl @Inject constructor(
    private val arrestClient: ArrestClient,
) : GetArrestRepository {
    override fun getLastMyArrestList(id: String): Flow<List<Arrest>> = callbackFlow<List<Arrest>> {
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
    }.flowOn(Dispatchers.IO)

    override fun getMyArrestList(id: String): Flow<Map<LocalDate, List<Arrest>>> = callbackFlow<Map<LocalDate, List<Arrest>>>{
        arrestClient.getMyArrestList(
            id = id,
            onSuccess = {
                trySend(it?.toArrestMap() ?: emptyMap())
            },
            onError = { e ->
                close(e)
            }
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    override fun getArrestList(): Flow<List<Arrest>> = callbackFlow<List<Arrest>>{
        arrestClient.getArrestList(
            onSuccess = {
                trySend(it?.toArrestList() ?: emptyList())
            },
            onError = { e ->
                close(e)
            }
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)
}
