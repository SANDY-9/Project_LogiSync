package com.sandy.logisync.data.wearable

import com.sandy.logisync.model.HeartRate

interface WearableTranscriptionRepository {
    suspend fun resultLogin(loginSuccess: String)
    suspend fun resultMeasuredHeartRate(heartRate: HeartRate)
}
