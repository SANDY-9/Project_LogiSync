package com.sandy.logisync.data.wearable

import com.sandy.logisync.data.mapper.toJson
import com.sandy.logisync.model.HeartRate
import com.sandy.logisync.wearable.message.MyWearableClient
import com.sandy.logisync.wearable.message.TranscriptionPath
import javax.inject.Inject

class WearableTranscriptionRepositoryImpl @Inject constructor(
    private val wearableClient: MyWearableClient
) : WearableTranscriptionRepository {
    override suspend fun resultLogin(loginSuccess: String) {
        wearableClient.requestTranscription(loginSuccess, TranscriptionPath.SEND_LOGIN_RESPONSE)
    }
    override suspend fun resultMeasuredHeartRate(heartRate: HeartRate) {
        wearableClient.requestTranscription(heartRate.toJson(), TranscriptionPath.SEND_HEART_RATE)
    }
}
