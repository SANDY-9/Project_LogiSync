package com.sandy.logisync.data.network

import android.accounts.AuthenticatorException
import com.sandy.logisync.data.datastore.WearableDataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import java.time.LocalDateTime
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val firebaseClient: MyFirebaseClient,
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
}
