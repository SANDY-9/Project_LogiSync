package com.sandy.logisync.data.network

import android.location.Location
import android.util.Log
import com.sandy.logisync.data.mapper.toCriticalPoint
import com.sandy.logisync.model.Arrest.ArrestType
import com.sandy.logisync.model.CriticalPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
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
            Log.e("확인", "getHeartRateCriticalPoint: 어흠?", )
            firebaseClient.getHeartRateCriticalPoint(
                id = id,
                onSuccess = {
                    Log.e("확인", "getHeartRateCriticalPoint: $it", )
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
                    close(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateArrest(
        id: String,
        arrestType: ArrestType,
        location: Location,
        token: String,
    ) = withContext(Dispatchers.IO) {
        callbackFlow {
            val time = LocalDateTime.now()
            firebaseClient.updateArrest(
                id = id,
                arrestType = arrestType.name,
                lat = location.latitude,
                lng = location.longitude,
                time = time,
                onSuccess = {
                    trySend(it)
                },
                onError = { error ->
                    close(error)
                }
            )
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun notifyArrest(id: String, token: String): Flow<String?> {
        Log.e("확인", "notifyArrest: 푸시알림", )
        val time = LocalDateTime.now()
        return withContext(Dispatchers.IO)  {
            Log.e("확인", "notifyArrest: 이건가?", )
            messagingClient.sendArrestMessage(token, id, time).map {
                Log.e("확인", "notifyArrest: $it", )
                it.body?.string().also {
                    Log.i("[NOTIFY_ARREST]", "notifyArrest: $it", )
                }
            }.catch {
                Log.e("확인", "notifyArrest: $it", )
            }.flowOn(Dispatchers.IO)
        }
    }
}
