package com.sandy.logisync.data.network

import android.location.Location
import com.sandy.logisync.model.Arrest.ArrestType
import com.sandy.logisync.model.CriticalPoint
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface NetworkRepository {

    suspend fun getHeartRateCriticalPoint(id: String): Flow<CriticalPoint>

    suspend fun updateHeartRate(
        id: String,
        bpm: Int,
        time: LocalDateTime,
    ): Flow<Boolean>

    suspend fun updateArrest(
        id: String,
        arrestType: ArrestType,
        location: Location,
        token: String,
    ): Flow<Boolean>

    suspend fun notifyArrest(
        id: String,
        token: String,
    ): Flow<String?>

}
