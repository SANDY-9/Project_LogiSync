package com.sandy.logisync.data.network

import android.accounts.AuthenticatorException
import android.location.Location
import android.util.Log
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import com.sandy.logisync.model.Arrest.ArrestType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val firebaseClient: MyFirebaseClient,
    private val messagingClient: MyMessagingClient,
    private val wearableDataStoreRepository: WearableDataStoreRepository,
) : NetworkRepository {

    override fun updateHeartRate(bpm: Int, time: LocalDateTime): Flow<Boolean> =
        callbackFlow {
            val account = wearableDataStoreRepository.getAccount().first()
            if (account != null) {
                firebaseClient.updateHeartRate(
                    id = account.id,
                    bpm = bpm,
                    time = time,
                    onSuccess = {
                        trySend(true)
                    },
                    onError = { error ->
                        //에러가 발생한 심박수 저장하는 로직 추가하기
                        close(error)
                    }
                )
            }
            else {
                close(AuthenticatorException())
                //에러가 발생한 심박수 저장하는 로직 추가하기
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)

    override fun updateArrest(
        id: String,
        arrestType: ArrestType,
        location: Location,
        token: String,
    ) = callbackFlow {
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

    override suspend fun notifyArrest(id: String, token: String): Flow<String?> {
        val time = LocalDateTime.now()
        return withContext(Dispatchers.IO)  {
            messagingClient.sendArrestMessage(token, id, time).map {
                it.body?.string().also {
                    Log.i("[NOTIFY_ARREST]", "notifyArrest: $it", )
                }
            }
        }
    }
}
