package com.sandy.logisync.data.network

import android.location.Location
import com.sandy.logisync.data.mapper.toCriticalPoint
import com.sandy.logisync.model.Arrest.ArrestType
import com.sandy.logisync.model.CriticalPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val firebaseClient: MyFirebaseClient,
    private val messagingClient: MyMessagingClient,
) : NetworkRepository {

    override suspend fun getHeartRateCriticalPoint(id: String): Flow<CriticalPoint> = withContext(Dispatchers.IO) {
        callbackFlow {
            firebaseClient.getHeartRateCriticalPoint(
                id = id,
                onSuccess = {
                    trySend(it.toCriticalPoint())
                },
                onError = { error ->
                    close(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateHeartRate(
        id: String,
        bpm: Int,
        time: LocalDateTime,
    ): Flow<Boolean> = withContext(Dispatchers.IO) {
        callbackFlow {
            firebaseClient.updateHeartRate(
                id = id,
                bpm = bpm,
                time = time,
                onSuccess = {
                    trySend(true)
                },
                onError = { error ->
                    error(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateNormalArrest(
        id: String,
        name: String,
        tel: String,
        arrestType: ArrestType,
        location: Location
    ): Flow<Boolean> = withContext(Dispatchers.IO) {
        callbackFlow {
            val time = LocalDateTime.now()
            firebaseClient.updateArrest(
                id = id,
                arrestType = arrestType.name,
                lat = location.latitude,
                lng = location.longitude,
                time = time,
                tel = tel,
                name = name,
                onSuccess = { isSuccessful ->
                    trySend(isSuccessful)
                },
                onError = { error ->
                    close(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateHeartBeatArrest(
        id: String,
        name: String,
        tel: String,
        arrestType: ArrestType,
        location: Location,
        bpm: Int
    ): Flow<Boolean> = withContext(Dispatchers.IO) {
        callbackFlow {
            val time = LocalDateTime.now()
            firebaseClient.updateArrest(
                id = id,
                arrestType = arrestType.name,
                lat = location.latitude,
                lng = location.longitude,
                name = name,
                tel = tel,
                time = time,
                bpm = bpm,
                onSuccess = { isSuccessful ->
                    trySend(isSuccessful)
                },
                onError = { error ->
                    close(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    private fun getToken(id: String): Flow<String> = callbackFlow {
        firebaseClient.getToken(
            id = id,
            onSuccess = { token ->
                trySend(token)
            },
            onError = { error ->
                close(error)
            }
        )
        awaitClose()
    }.flowOn(Dispatchers.IO)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun notifyArrest(id: String): Flow<String?> {
        val time = LocalDateTime.now()
        return withContext(Dispatchers.IO)  {
            getToken(id).flatMapLatest { token ->
                messagingClient.sendArrestMessage(token, id, time).map {
                    it.body?.string()
                }
            }.flowOn(Dispatchers.IO)
        }
    }

}
