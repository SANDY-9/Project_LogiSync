package com.core.data.repository.wearable

import com.core.domain.repository.WearableRepository
import com.sandy.bluetooth.MyWearableClient
import com.sandy.bluetooth.Transcription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val INTERVAL = 1200L

class WearableRepositoryImpl @Inject constructor(
    private val wearableClient: MyWearableClient,
) : WearableRepository {

    // 테스트용
    override fun getWearableConnectState(): Flow<Boolean> = flow {
        val isConnected = wearableClient.isConnectWearable()
        emit(isConnected)
        /*while (true) {
            val isConnected = wearableClient.isConnectWearable()
            emit(isConnected)
            delay(INTERVAL)
        }*/
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

    override suspend fun sendLogin(id: String) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(data = id, transcription = Transcription.LOGIN)
    }

    override suspend fun requestCollectHeartRate(id: String) = withContext(Dispatchers.IO) {
        wearableClient.requestTranscription(
            data = id,
            transcription = Transcription.REQUEST_HEAT_RATE
        )
    }
}
